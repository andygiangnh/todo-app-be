package com.mytodo.exception;

public class ExistingEmailFoundException extends Exception {

    public ExistingEmailFoundException(String message) {
        super(message);
    }
}
