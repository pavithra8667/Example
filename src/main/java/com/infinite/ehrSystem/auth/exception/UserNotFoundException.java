package com.infinite.ehrSystem.auth.exception;

public class UserNotFoundException
        extends RuntimeException {

    public UserNotFoundException(
            String message) {
        super(message);
    }
}