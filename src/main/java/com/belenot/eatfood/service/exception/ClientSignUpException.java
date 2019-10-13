package com.belenot.eatfood.service.exception;

import com.belenot.eatfood.exception.EatfoodException;

public class ClientSignUpException extends EatfoodException {
    public ClientSignUpException(String message) {
        super(message);
    }

    public ClientSignUpException(String message, EatfoodExceptionType type) {
        super(message, type);
    }
}