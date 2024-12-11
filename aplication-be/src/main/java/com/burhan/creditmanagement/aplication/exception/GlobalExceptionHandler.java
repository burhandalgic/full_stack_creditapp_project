package com.burhan.creditmanagement.aplication.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomerAndCreditTypeExistException.class)
    public ResponseEntity<String> handleCustomException(CustomerAndCreditTypeExistException ex) {
        // Hata mesajını ve HTTP status kodunu dönüyoruz
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
