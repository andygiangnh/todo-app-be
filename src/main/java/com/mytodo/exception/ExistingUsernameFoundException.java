package com.mytodo.exception;

public class ExistingUsernameFoundException extends Exception {

    public ExistingUsernameFoundException(String message) {
        super(message);
    }
}
