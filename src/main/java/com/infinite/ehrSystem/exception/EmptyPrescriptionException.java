package com.infinite.ehrSystem.exception;

public class EmptyPrescriptionException
        extends RuntimeException {

    public EmptyPrescriptionException(
            String message) {

        super(message);
    }
}