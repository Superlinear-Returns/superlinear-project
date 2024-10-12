package com.eight.common.module.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseException extends RuntimeException {
    private String statusCode;
    private String message;

    public BaseException(String statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
        this.message = message;
    }

}
