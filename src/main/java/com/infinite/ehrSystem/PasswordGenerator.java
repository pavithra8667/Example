package com.infinite.ehrSystem;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {

    public static void main(String[] args) {

        BCryptPasswordEncoder encoder =
                new BCryptPasswordEncoder();

        System.out.println("Doctor123 = "
                + encoder.encode("Doctor123"));

        System.out.println("Lab123 = "
                + encoder.encode("Lab123"));

        System.out.println("Patient123 = "
                + encoder.encode("Patient123"));
    }
}