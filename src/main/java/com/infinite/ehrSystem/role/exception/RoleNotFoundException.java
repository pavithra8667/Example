package com.infinite.ehrSystem.role.exception;

public class RoleNotFoundException
        extends RuntimeException {

    public RoleNotFoundException(
            String message) {
        super(message);
    }
}