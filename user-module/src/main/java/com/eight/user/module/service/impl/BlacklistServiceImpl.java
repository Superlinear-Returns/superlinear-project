package com.eight.user.module.service.impl;

import com.eight.user.module.constant.StatusCode;
import com.eight.user.module.core.exception.BaseException;
import com.eight.user.module.model.Blacklist;
import com.eight.user.module.repository.IBlacklistRepo;
import com.eight.user.module.service.BlacklistService;
import org.springframework.stereotype.Service;

@Service
public class BlacklistServiceImpl implements BlacklistService {

    private final IBlacklistRepo blacklistRepo;

    public BlacklistServiceImpl(IBlacklistRepo blacklistRepo) {
        this.blacklistRepo = blacklistRepo;
    }

    @Override
    public void checkBlacklist(String ipAddress) {
        blacklistRepo.findByIpAddress(ipAddress).ifPresent(blacklist -> {
            throw new BaseException(StatusCode.IP_BLOCKED, "IP is blocked");
        });
    }

    @Override
    public void addBlacklist(String ipAddress, String remark) {
        blacklistRepo.save(new Blacklist(ipAddress, remark));
    }
}
