package com.spharos.ssgpoint.exception;


import com.spharos.ssgpoint.user.presentation.UserController;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.BadCredentialsException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.lang.module.ResolutionException;



@Slf4j
@RestControllerAdvice
public class ExceptionController  {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResult> userExHandle(IllegalArgumentException e) {
        ErrorResult errorResult = new ErrorResult("error",e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResult> loginExHandle(BadCredentialsException e) {
        ErrorResult errorResult = new ErrorResult("아이디 또는 비밀번호를 확인해 주세요 ", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResult> customExHandle(CustomException e) {
        ErrorResult errorResult = new ErrorResult("커스텀 에러", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ErrorResult> handleSignatureException(SignatureException e) {
        ErrorResult errorResult = new ErrorResult("토큰이 유효하지 않습니다.", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<ErrorResult> handleMalformedJwtException(MalformedJwtException e) {
        ErrorResult errorResult = new ErrorResult("올바르지않은 토큰", e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorResult> handleExpiredJwtException(ExpiredJwtException e) {
        ErrorResult errorResult = new ErrorResult("토큰이 만료되었습니다2222.",e.getMessage());
        return new ResponseEntity<>(errorResult, HttpStatus.FORBIDDEN);
    }

}
