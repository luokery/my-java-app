package com.example.demo.exception;

import lombok.Getter;

/**
 * Enum representing application-specific error codes and messages.
 */
@Getter
public enum ErrorCode {

    UNKONW_ERROR(500, "未知错误"),
    SUCCESS(200, "成功"),
    ERROR(1, "失败"),
    // 400 Bad Request
    VALIDATION_ERROR(400, "Validation failed"),

    // 404 Not Found
    PRODUCT_NOT_FOUND(404, "Product not found"),
    CUSTOMER_NOT_FOUND(404, "Customer not found"),

    // 500 Internal Server Error
    INTERNAL_SERVER_ERROR(500, "An unexpected internal server error occurred");

    private final int httpStatus;
    private final String message;

    ErrorCode(int httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
