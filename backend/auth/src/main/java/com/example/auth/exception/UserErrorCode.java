package com.example.auth.exception;

import com.example.common.exception.BaseExceptionAssert;
import lombok.Getter;

//用户服务2000 ~ 2999
@Getter
public enum UserErrorCode implements BaseExceptionAssert {

    USER_NOT_FOUND(2001, "用户不存在"),

    WRONG_PASSWORD(2002,"密码错误"),
    USER_HAS_REGISTERED(2003,"用户已注册"),
    PASSWORD_HAS_EXISTED(2004,"密码已存在");

    private final int code;
    private final String message;

    UserErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
