package com.challenge.voting.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity handleNotValidException(RuntimeException exception){

        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
