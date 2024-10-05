package com.eight.product.module.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductBaseException extends RuntimeException{
    private String statusCode;
    private String message;

    public ProductBaseException(String statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
        this.message = message;
    }
}
