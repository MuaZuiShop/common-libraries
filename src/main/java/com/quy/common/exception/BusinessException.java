package com.quy.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

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