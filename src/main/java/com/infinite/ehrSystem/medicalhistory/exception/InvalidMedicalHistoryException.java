package com.infinite.ehrSystem.medicalhistory.exception;

public class InvalidMedicalHistoryException
        extends RuntimeException {

    public InvalidMedicalHistoryException(
            String message) {
        super(message);
    }
}