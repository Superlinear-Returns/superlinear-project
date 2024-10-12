package com.eight.common.module.exception;

import com.eight.common.module.constant.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseExceptionResponseTO> handleExceptionToResponse(Exception e) {
        BaseExceptionResponseTO baseExceptionResponseTO = new BaseExceptionResponseTO(StatusCode.UNKNOW_ERR, e.getMessage());
        return new ResponseEntity<>(baseExceptionResponseTO, HttpStatus.OK);
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<BaseExceptionResponseTO> handleBaseExceptionToResponse(BaseException e) {
        BaseExceptionResponseTO baseExceptionResponseTO = new BaseExceptionResponseTO(e.getStatusCode(), e.getMessage());
        return new ResponseEntity<>(baseExceptionResponseTO, HttpStatus.OK);
    }
}
