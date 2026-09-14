package com.infinite.ehrSystem.exception;

public class LabOrderNotFoundException
        extends RuntimeException {

    public LabOrderNotFoundException(
            String message) {

        super(message);
    }
}