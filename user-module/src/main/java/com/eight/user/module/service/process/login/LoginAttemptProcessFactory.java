package com.eight.user.module.service.process.login;

import com.eight.user.module.constant.StatusCode;
import com.eight.user.module.core.exception.BaseException;
import org.springframework.stereotype.Component;

@Component
public class LoginAttemptProcessFactory {

    private final IncorrectPasswordProcess incorrectPasswordProcess;

    private final IncorrectUsernameProcess incorrectUsernameProcess;

    public LoginAttemptProcessFactory(IncorrectPasswordProcess incorrectPasswordProcess, IncorrectUsernameProcess incorrectUsernameProcess) {
        this.incorrectPasswordProcess = incorrectPasswordProcess;
        this.incorrectUsernameProcess = incorrectUsernameProcess;
    }

    public LoginAttemptProcess getProcess(String incorrectType) {
        return switch (incorrectType) {
            case StatusCode.PASSWORD_ERR -> incorrectPasswordProcess;
            case StatusCode.USER_NOT_FOUND -> incorrectUsernameProcess;
            default -> throw new BaseException(StatusCode.UNKNOW_ERR, "Incorrect type not supported");
        };
    }

}
