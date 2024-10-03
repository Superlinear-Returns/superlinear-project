package com.eight.user.module.core.exception;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseExceptionResponseTO implements Serializable {

    private String statusCode;
    private String message;

    public BaseExceptionResponseTO(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
