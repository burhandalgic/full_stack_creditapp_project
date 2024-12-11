package com.burhan.creditmanagement.creditapp_bff.customexeption;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomApplicationException extends RuntimeException{
    private final String message;
    private final int statusCode;
}

