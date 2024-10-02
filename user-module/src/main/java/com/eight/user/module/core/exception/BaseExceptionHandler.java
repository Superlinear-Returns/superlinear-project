package com.eight.user.module.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<BaseExceptionResponseTO> handleBaseExceptionToResponse(BaseException e) {
        BaseExceptionResponseTO baseExceptionResponseTO = new BaseExceptionResponseTO(e.getStatusCode(), e.getMessage());
        return new ResponseEntity<>(baseExceptionResponseTO, HttpStatus.BAD_REQUEST);
    }
}
