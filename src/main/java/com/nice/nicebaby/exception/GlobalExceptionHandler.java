package com.nice.nicebaby.exception;

import com.nice.nicebaby.util.HttpResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.nice.nicebaby.define.ErrorId.JWTToken_Invalid;
import static com.nice.nicebaby.define.ErrorId.JWT_TOKEN_EXPIRED;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JWTException.class)
    public ResponseEntity<HttpResult> handle(JWTException ex) {
        HttpResult result = new HttpResult(JWTToken_Invalid, ex.getMessage());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(result);
    }

    @ExceptionHandler(JWTExpiredException.class)
    public ResponseEntity<HttpResult> handle(JWTExpiredException ex) {
        HttpResult result = new HttpResult(JWT_TOKEN_EXPIRED, ex.getMessage());
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body(result);
    }

}
