package com.example.common.exception;

public enum CommonErrorCode implements BaseExceptionAssert {
    PARAM_MISSING(1001, "参数缺失"),
    UNAUTHORIZED(1002, "未授权"),
    ORDER_NOT_FOUND(1003, "订单不存在");

    private final int code;
    private final String message;

    CommonErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() { return code; }
    public String getMessage() { return message; }
}
