package com.infinite.ehrSystem.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthDTO {

    private String username;

    private String password;

    private String email;

    private String role;
}