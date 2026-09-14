package com.infinite.ehrSystem.patient.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class PatientDto {

    private UUID id;

    private String patientCode;

    @NotBlank(message = "Name is required")
    @Size(
            min = 3,
            max = 80,
            message = "Name must be between 3 and 80 characters"
    )
    private String name;

    @NotNull(message = "Date of birth is required")
    @PastOrPresent(message = "Date of birth cannot be in the future")
    private LocalDate dob;

    private String gender;

    @NotBlank(message = "Phone is required")
    @Pattern(
            regexp = "\\d{10}",
            message = "Phone must contain exactly 10 digits"
    )
    private String phone;

    private String guardianName;
}
