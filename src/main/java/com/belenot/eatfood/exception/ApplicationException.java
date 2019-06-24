package com.belenot.eatfood.exception;

public class ApplicationException extends Exception {
    public ApplicationException() { super(); }
    public ApplicationException(String msg) { super(msg); }
    public ApplicationException(String msg, Throwable throwable) { super(msg, throwable); }
}
