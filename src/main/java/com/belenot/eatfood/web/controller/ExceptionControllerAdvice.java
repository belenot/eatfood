package com.belenot.eatfood.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler
    public ResponseEntity<String> handleException(Exception exc) {
        return new ResponseEntity<String>(exc.getMessage(), HttpStatus.BAD_REQUEST);
    }
}