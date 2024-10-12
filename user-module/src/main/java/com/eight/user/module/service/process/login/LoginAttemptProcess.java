package com.eight.user.module.service.process.login;

import com.eight.common.module.constant.StatusCode;
import com.eight.common.module.exception.BaseException;
import com.eight.user.module.service.BlacklistService;
import com.eight.user.module.utils.HostAddressUtils;
import jakarta.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public abstract class LoginAttemptProcess {

    private static final Logger log = LoggerFactory.getLogger(LoginAttemptProcess.class);
    private final ConcurrentHashMap<String, LoginAttemptRecord> loginAttemptCache = new ConcurrentHashMap<>();

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private static final int  MAX_ATTEMPT = 5;

    private final BlacklistService blacklistService;

    public LoginAttemptProcess(BlacklistService blacklistService) {
        this.blacklistService = blacklistService;
        scheduleCleanTask();
    }

    @Getter
    @Setter
    private static class LoginAttemptRecord {
        private int attempt;
        private LocalDateTime lastAttemptTime;

        public void incrementAttempt() {
            attempt++;
        }

        public void resetAttempt() {
            attempt = 0;
        }
    }

    public void processLoginAttempt() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        var ip = HostAddressUtils.getClientIpAddress(request);
        var key = getIncorrectType() + ":" + ip;
        LoginAttemptRecord loginAttemptRecord = loginAttemptCache.get(key);

        if (loginAttemptRecord == null) {
            loginAttemptRecord = new LoginAttemptRecord();
            loginAttemptCache.put(key, loginAttemptRecord);
            loginAttemptRecord.setLastAttemptTime(LocalDateTime.now());
            loginAttemptRecord.incrementAttempt();

        } else if (loginAttemptRecord.getAttempt() >= MAX_ATTEMPT) {
            loginAttemptRecord.setLastAttemptTime(LocalDateTime.now());
            blacklistService.addBlacklist(ip, getIncorrectType());
            throw new BaseException(StatusCode.IP_BLOCKED, "IP is blocked");

        } else {
            if (loginAttemptRecord.getLastAttemptTime().isBefore(LocalDateTime.now().minusMinutes(5))) {
                loginAttemptRecord.resetAttempt();
            }
            loginAttemptRecord.setLastAttemptTime(LocalDateTime.now());
        }

        var remainingAttempt = MAX_ATTEMPT - loginAttemptRecord.getAttempt();
        var message = (remainingAttempt == 1) ? "last one" : String.valueOf(remainingAttempt);
        loginAttemptRecord.incrementAttempt();
        throw new BaseException(StatusCode.REQ_PARAM_ERR, String.format("Incorrect %s, remaining %s attempt times",
                getIncorrectType(), message));
    }

    private void scheduleCleanTask() {
        scheduler.scheduleAtFixedRate(this::cleanCache, 0, 60, TimeUnit.MINUTES);
    }

    private void cleanCache() {
        log.info("Cleaning login attempt cache");
        var expireTime = LocalDateTime.now().minusMinutes(5);
        loginAttemptCache.entrySet().removeIf(entry ->
                entry.getValue().getLastAttemptTime().isBefore(expireTime));
    }

    public void shutdown() {
        scheduler.shutdown();
    }

    public abstract String getIncorrectType();
}
