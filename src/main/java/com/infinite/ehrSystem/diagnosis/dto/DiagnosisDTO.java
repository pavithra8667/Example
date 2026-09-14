package com.infinite.ehrSystem.diagnosis.dto;

import com.infinite.ehrSystem.diagnosis.entity.DiagnosisType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiagnosisDTO {

    private UUID id;

    @NotNull(message = "Visit ID is required")
    private UUID visitId;

    @NotBlank(message = "Diagnosis code is required")
    @Size(max = 20, message = "Diagnosis code must not exceed 20 characters")
    private String diagnosisCode;

    @NotBlank(message = "Diagnosis description is required")
    @Size(max = 255, message = "Description must not exceed 255 characters")
    private String description;

    @NotNull(message = "Diagnosis type is required")
    private DiagnosisType diagnosisType;
}