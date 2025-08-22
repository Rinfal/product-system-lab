package com.example.common.exception;

import com.example.common.pojo.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseMessage<Void>> handleBusinessException(BusinessException e) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResponseMessage.error(e.getCode(), e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseMessage<Void>> handleOtherException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResponseMessage.error(500, "系统异常：" + e.getMessage()));
    }
}
