package com.example.product.exception;


import com.example.common.exception.BaseExceptionAssert;
import lombok.Getter;

//用户服务3000-3999
@Getter
public enum ProductErrorCode implements BaseExceptionAssert {

    UPDATE_NULL(3001, "字段不能为空");

    private final int code;
    private final String message;

    ProductErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
