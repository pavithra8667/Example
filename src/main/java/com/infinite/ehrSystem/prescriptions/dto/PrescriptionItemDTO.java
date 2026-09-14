package com.infinite.ehrSystem.prescriptions.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionItemDTO {

    private UUID itemId;
    @NotBlank(message = "Medicine name is required")
    private String medicineName;

    @NotBlank(message = "Dosage is required")
    @Size(max = 40, message = "Dosage must not exceed 40 characters")
    private String dosage;

    @NotBlank(message = "Frequency is required")
    @Size(max = 40, message = "Frequency must not exceed 40 characters")
    private String frequency;

    @NotNull(message = "Duration days is required")
    @Min(value = 1, message = "Duration must be at least 1 day")
    @Max(value = 90, message = "Duration must not exceed 90 days")
    private Integer durationDays;
}