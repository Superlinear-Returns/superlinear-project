package com.eight.user.module.service.process.login;

import com.eight.user.module.service.BlacklistService;
import org.springframework.stereotype.Component;

@Component
public class IncorrectPasswordProcess extends LoginAttemptProcess{

    public IncorrectPasswordProcess(BlacklistService blacklistService) {
        super(blacklistService);
    }

    @Override
    public String getIncorrectType() {
        return "password";
    }
}
