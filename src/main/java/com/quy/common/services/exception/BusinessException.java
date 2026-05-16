package com.quy.common.services.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final Code code;

    public BusinessException(Code code) {
        super(code.getMessage());
        this.code = code;
    }

    public BusinessException(Code code, String message) {
        super(message);
        this.code = code;
    }
}