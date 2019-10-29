package com.belenot.eatfood.exception;

public class EatfoodException extends RuntimeException {
    public enum EatfoodExceptionType {INCORRECT_PASSWORD, NO_SUCH_CLIENT, CLIENT_ALREADY_EXISTS, UNKNOWN};
    private EatfoodExceptionType type;
    public EatfoodException(String message) {
        super(message);
        this.type = EatfoodExceptionType.UNKNOWN;
    }

    public EatfoodException(String message, EatfoodExceptionType type) {
        super(message);
        this.type = type;
    }

    public EatfoodExceptionType getType() {
        return type;
    }

    public void setType(EatfoodExceptionType type) {
        this.type = type;
    }
}