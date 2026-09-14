package com.infinite.ehrSystem.exception;

public class PrescriptionNotFoundException
        extends RuntimeException {

    public PrescriptionNotFoundException(
            String message) {

        super(message);
    }
}