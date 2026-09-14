package com.infinite.ehrSystem.allergy.exception;

public class DuplicateAllergyException
        extends RuntimeException {

    public DuplicateAllergyException(
            String message) {
        super(message);
    }
}