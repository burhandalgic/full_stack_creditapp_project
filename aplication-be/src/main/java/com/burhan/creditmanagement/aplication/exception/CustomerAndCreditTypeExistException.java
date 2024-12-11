package com.burhan.creditmanagement.aplication.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerAndCreditTypeExistException extends RuntimeException{
    private final String message;
    private final int statusCode;
}

