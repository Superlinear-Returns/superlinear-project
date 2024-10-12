package com.eight.user.module.service.process.login;

import com.eight.user.module.service.BlacklistService;
import org.springframework.stereotype.Component;

@Component
public class IncorrectUsernameProcess extends LoginAttemptProcess {

    public IncorrectUsernameProcess(BlacklistService blacklistService) {
        super(blacklistService);
    }

    @Override
    public String getIncorrectType() {
        return "username";
    }
}
