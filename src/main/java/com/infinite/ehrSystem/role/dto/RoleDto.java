package com.infinite.ehrSystem.role.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RoleDto {

    private UUID id;

    @NotBlank(message = "Role name is required")
    private String name;
}