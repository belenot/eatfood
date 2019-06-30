package com.belenot.eatfood.web.controller;

import com.belenot.eatfood.exception.ApplicationException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ApplicationExceptionHandlerController {

    @ExceptionHandler
    @ResponseBody
    public String handleApplicationException(ApplicationException exc) {
	return exc.getMessage();
    }
}
