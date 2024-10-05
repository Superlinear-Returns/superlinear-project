package com.eight.product.module.exception;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class ProductBaseResponseTo implements Serializable {
    private String statusCode;
    private String message;
    public ProductBaseResponseTo(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
