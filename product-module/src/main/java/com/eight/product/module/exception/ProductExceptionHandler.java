package com.eight.product.module.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionHandler {
    @ExceptionHandler(ProductBaseException.class)
    public ResponseEntity<ProductBaseResponseTo> handleProductBaseException(ProductBaseException e){
        ProductBaseResponseTo response = new ProductBaseResponseTo(e.getStatusCode(), e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
