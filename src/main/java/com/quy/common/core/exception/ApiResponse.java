package com.quy.common.core.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    private String code;
    private String message;
    private T data;

    @Builder.Default
    private ZonedDateTime timestamp = ZonedDateTime.now(ZoneId.systemDefault());

    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
            .code("SUCCESS")
            .data(data)
            .build();
    }

    public static <T> ApiResponse<T> success() {
        return ApiResponse.<T>builder()
            .code("SUCCESS")
            .build();
    }

    public static <T> ApiResponse<T> error(String code, String message) {
        return ApiResponse.<T>builder()
            .code(code)
            .message(message)
            .build();
    }

    public static <T> ApiResponse<T> error(ErrorCode errorCode) {
        return ApiResponse.<T>builder()
            .code(errorCode.getCode())
            .message(errorCode.getMessage())
            .build();
    }
}