package com.belenot.eatfood.service.exception;

import com.belenot.eatfood.exception.EatfoodException;

public class ClientSignInException extends EatfoodException {
    public ClientSignInException(String message) {
        super(message);
    }

    public ClientSignInException(String message, EatfoodExceptionType type) {
        super(message, type);
    }
}