package com.sayra.umai.exception;

import org.springframework.http.HttpStatus;

public class ValidationException extends BusinessException {
    public ValidationException(String message) {
        super(message, HttpStatus.BAD_REQUEST.value(), "VALIDATION_ERROR");
    }
}
