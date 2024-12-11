package com.burhan.customermanagement.customer.exception;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomException extends RuntimeException{
    private final String message;
    private final int statusCode;
}

