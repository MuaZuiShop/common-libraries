package com.quy.common.core.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // ==================== COMMON ====================
    UNAUTHORIZED(
        "UNAUTHORIZED", ErrorMessage.VI.UNAUTHORIZED, HttpStatus.UNAUTHORIZED),
    FORBIDDEN(
        "FORBIDDEN", ErrorMessage.VI.FORBIDDEN, HttpStatus.FORBIDDEN),
    NOT_FOUND(
        "NOT_FOUND", ErrorMessage.VI.NOT_FOUND, HttpStatus.NOT_FOUND),
    INTERNAL_ERROR(
        "INTERNAL_ERROR", ErrorMessage.VI.INTERNAL_ERROR, HttpStatus.INTERNAL_SERVER_ERROR),
    VALIDATION_ERROR(
        "VALIDATION_ERROR", ErrorMessage.VI.VALIDATION_ERROR, HttpStatus.BAD_REQUEST),
    BAD_REQUEST(
        "BAD_REQUEST", ErrorMessage.VI.BAD_REQUEST, HttpStatus.BAD_REQUEST),
    SERVICE_UNAVAILABLE(
        "SERVICE_UNAVAILABLE", ErrorMessage.VI.SERVICE_UNAVAILABLE, HttpStatus.SERVICE_UNAVAILABLE),
    GATEWAY_TIMEOUT(
        "GATEWAY_TIMEOUT", ErrorMessage.VI.GATEWAY_TIMEOUT, HttpStatus.GATEWAY_TIMEOUT),

    // ==================== AUTH ====================
    AUTH_INVALID_CREDENTIALS(
        "AUTH_INVALID_CREDENTIALS", ErrorMessage.VI.AUTH_INVALID_CREDENTIALS, HttpStatus.UNAUTHORIZED),
    AUTH_ACCOUNT_LOCKED(
        "AUTH_ACCOUNT_LOCKED", ErrorMessage.VI.AUTH_ACCOUNT_LOCKED, HttpStatus.FORBIDDEN),
    AUTH_ACCOUNT_DISABLED(
        "AUTH_ACCOUNT_DISABLED", ErrorMessage.VI.AUTH_ACCOUNT_DISABLED, HttpStatus.FORBIDDEN),
    AUTH_TOKEN_INVALID(
        "AUTH_TOKEN_INVALID", ErrorMessage.VI.AUTH_TOKEN_INVALID, HttpStatus.UNAUTHORIZED),
    AUTH_TOKEN_EXPIRED(
        "AUTH_TOKEN_EXPIRED", ErrorMessage.VI.AUTH_TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED),
    AUTH_TOKEN_MISSING(
        "AUTH_TOKEN_MISSING", ErrorMessage.VI.AUTH_TOKEN_MISSING, HttpStatus.UNAUTHORIZED),
    AUTH_REFRESH_TOKEN_INVALID(
        "AUTH_REFRESH_TOKEN_INVALID", ErrorMessage.VI.AUTH_REFRESH_TOKEN_INVALID, HttpStatus.UNAUTHORIZED),
    AUTH_REFRESH_TOKEN_EXPIRED(
        "AUTH_REFRESH_TOKEN_EXPIRED", ErrorMessage.VI.AUTH_REFRESH_TOKEN_EXPIRED, HttpStatus.UNAUTHORIZED),
    AUTH_REFRESH_TOKEN_MISSING(
        "AUTH_REFRESH_TOKEN_MISSING", ErrorMessage.VI.AUTH_REFRESH_TOKEN_MISSING, HttpStatus.UNAUTHORIZED),
    AUTH_USERNAME_EXISTED(
        "AUTH_USERNAME_EXISTED", ErrorMessage.VI.AUTH_USERNAME_EXISTED, HttpStatus.CONFLICT),
    AUTH_EMAIL_EXISTED(
        "AUTH_EMAIL_EXISTED", ErrorMessage.VI.AUTH_EMAIL_EXISTED, HttpStatus.CONFLICT),
    AUTH_PHONE_EXISTED(
        "AUTH_PHONE_EXISTED", ErrorMessage.VI.AUTH_PHONE_EXISTED, HttpStatus.CONFLICT),
    AUTH_REGISTER_FAILED(
        "AUTH_REGISTER_FAILED", ErrorMessage.VI.AUTH_REGISTER_FAILED, HttpStatus.BAD_REQUEST),
    AUTH_LOGIN_FAILED(
        "AUTH_LOGIN_FAILED", ErrorMessage.VI.AUTH_LOGIN_FAILED, HttpStatus.UNAUTHORIZED),
    AUTH_LOGOUT_FAILED(
        "AUTH_LOGOUT_FAILED", ErrorMessage.VI.AUTH_LOGOUT_FAILED, HttpStatus.INTERNAL_SERVER_ERROR),
    AUTH_PASSWORD_WRONG(
        "AUTH_PASSWORD_WRONG", ErrorMessage.VI.AUTH_PASSWORD_WRONG, HttpStatus.BAD_REQUEST),
    AUTH_PASSWORD_NOT_MATCH(
        "AUTH_PASSWORD_NOT_MATCH", ErrorMessage.VI.AUTH_PASSWORD_NOT_MATCH, HttpStatus.BAD_REQUEST),
    AUTH_PASSWORD_TOO_WEAK(
        "AUTH_PASSWORD_TOO_WEAK", ErrorMessage.VI.AUTH_PASSWORD_TOO_WEAK, HttpStatus.BAD_REQUEST),
    AUTH_OTP_INVALID(
        "AUTH_OTP_INVALID", ErrorMessage.VI.AUTH_OTP_INVALID, HttpStatus.BAD_REQUEST),
    AUTH_OTP_EXPIRED(
        "AUTH_OTP_EXPIRED", ErrorMessage.VI.AUTH_OTP_EXPIRED, HttpStatus.BAD_REQUEST),
    AUTH_OTP_SEND_FAILED(
        "AUTH_OTP_SEND_FAILED", ErrorMessage.VI.AUTH_OTP_SEND_FAILED, HttpStatus.INTERNAL_SERVER_ERROR),
    AUTH_REQUIRES_REFRESH(
        "AUTH_REQUIRES_REFRESH", ErrorMessage.VI.AUTH_REQUIRES_REFRESH, HttpStatus.UNAUTHORIZED),
    AUTH_SESSION_EXPIRED(
        "AUTH_SESSION_EXPIRED", ErrorMessage.VI.AUTH_SESSION_EXPIRED, HttpStatus.UNAUTHORIZED),
    AUTH_ROLE_NOT_FOUND(
        "AUTH_ROLE_NOT_FOUND", ErrorMessage.VI.AUTH_ROLE_NOT_FOUND, HttpStatus.NOT_FOUND),
    AUTH_PERMISSION_DENIED(
        "AUTH_PERMISSION_DENIED", ErrorMessage.VI.AUTH_PERMISSION_DENIED, HttpStatus.FORBIDDEN),

    // ==================== CUSTOMER ====================
    CUSTOMER_NOT_FOUND(
        "CUSTOMER_NOT_FOUND", ErrorMessage.VI.CUSTOMER_NOT_FOUND, HttpStatus.NOT_FOUND),
    CUSTOMER_ALREADY_EXISTS(
        "CUSTOMER_ALREADY_EXISTS", ErrorMessage.VI.CUSTOMER_ALREADY_EXISTS, HttpStatus.CONFLICT),
    CUSTOMER_EMAIL_EXISTED(
        "CUSTOMER_EMAIL_EXISTED", ErrorMessage.VI.CUSTOMER_EMAIL_EXISTED, HttpStatus.CONFLICT),
    CUSTOMER_PHONE_EXISTED(
        "CUSTOMER_PHONE_EXISTED", ErrorMessage.VI.CUSTOMER_PHONE_EXISTED, HttpStatus.CONFLICT),
    CUSTOMER_CREATE_FAILED(
        "CUSTOMER_CREATE_FAILED", ErrorMessage.VI.CUSTOMER_CREATE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR),
    CUSTOMER_UPDATE_FAILED(
        "CUSTOMER_UPDATE_FAILED", ErrorMessage.VI.CUSTOMER_UPDATE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR),
    CUSTOMER_DELETE_FAILED(
        "CUSTOMER_DELETE_FAILED", ErrorMessage.VI.CUSTOMER_DELETE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR),
    CUSTOMER_INACTIVE(
        "CUSTOMER_INACTIVE", ErrorMessage.VI.CUSTOMER_INACTIVE, HttpStatus.FORBIDDEN),
    CUSTOMER_ADDRESS_NOT_FOUND(
        "CUSTOMER_ADDRESS_NOT_FOUND", ErrorMessage.VI.CUSTOMER_ADDRESS_NOT_FOUND, HttpStatus.NOT_FOUND),
    CUSTOMER_ADDRESS_LIMIT(
        "CUSTOMER_ADDRESS_LIMIT", ErrorMessage.VI.CUSTOMER_ADDRESS_LIMIT, HttpStatus.BAD_REQUEST);

    private final String code;
    private final String message;
    private final HttpStatus httpStatus;

    // Tạo exception với message mặc định (VI)
    public AppException toException() {
        return new AppException(this.code, this.message, this.httpStatus);
    }

    // Tạo exception với message tùy chỉnh (dùng khi cần EN hoặc message động)
    public AppException toException(String customMessage) {
        return new AppException(this.code, customMessage, this.httpStatus);
    }

    // Tạo exception với message EN
    public AppException toExceptionEN() {
        try {
            java.lang.reflect.Field field = ErrorMessage.EN.class.getField(this.name());
            String enMessage = (String) field.get(null);
            return new AppException(this.code, enMessage, this.httpStatus);
        } catch (Exception e) {
            return toException(); // fallback về VI
        }
    }
}