package com.sayra.umai.exception;

import lombok.Getter;

@Getter
public abstract class BusinessException extends RuntimeException {
    private final int statusCode;
    private final String errorCode;
    protected BusinessException(String message, int statusCode, String errorCode) {
        super(message);
        this.statusCode = statusCode;
        this.errorCode = errorCode;
    }
}
