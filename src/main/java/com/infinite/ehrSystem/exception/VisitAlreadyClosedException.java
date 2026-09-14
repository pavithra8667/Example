package com.infinite.ehrSystem.exception;

public class VisitAlreadyClosedException
        extends RuntimeException {

    public VisitAlreadyClosedException(
            String message) {

        super(message);
    }
}