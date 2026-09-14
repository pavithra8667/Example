package com.infinite.ehrSystem.labResults.dto;

import com.infinite.ehrSystem.labResults.entity.LabOrderStatus;
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
public class LabOrderDTO {

    private UUID id;

    @NotNull(message = "Visit ID is required")
    private UUID visitId;

    @NotBlank(message = "Test name is required")
    @Size(max = 100, message = "Test name must not exceed 100 characters")
    private String testName;

    private LabOrderStatus status;
}