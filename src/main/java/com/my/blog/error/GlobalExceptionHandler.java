package com.my.blog.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.BindException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { CommonException.class })
    protected ResponseEntity<ErrorResponse> handleCommonException(CommonException e) {
        log.error("CommonException : {}", e.getErrorCode());
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }

    @ExceptionHandler(value = { BindException.class })
    protected ResponseEntity<ErrorResponse> handleBindException(BindException e) {
        log.error("BindException : {}", e.getMessage());
        return ErrorResponse.toResponseEntity(CommonErrorCode.INVALID_PARAMETER);
    }

}