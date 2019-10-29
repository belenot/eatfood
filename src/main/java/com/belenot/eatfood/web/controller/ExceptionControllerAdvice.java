package com.belenot.eatfood.web.controller;

import com.belenot.eatfood.exception.EatfoodException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(EatfoodException.class)
    public ResponseEntity<String> handleEatfoodException(EatfoodException exc) {
        return new ResponseEntity<String>(exc.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(Exception exc) {
        return new ResponseEntity<String>(exc.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}