package com.infinite.ehrSystem.allergy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AllergyDTO {

    private UUID id;

    private UUID patientId;

    @NotBlank(message = "Allergen is required")
    private String allergen;

    @NotBlank(message = "Reaction is required")
    private String reaction;

    @NotBlank(message = "Severity is required")
    @Pattern(
            regexp = "MILD|MODERATE|SEVERE",
            message = "Severity must be MILD, MODERATE or SEVERE"
    )
    private String severity;
}