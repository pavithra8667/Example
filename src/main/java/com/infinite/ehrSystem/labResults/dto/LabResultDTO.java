package com.infinite.ehrSystem.labResults.dto;

import com.infinite.ehrSystem.labResults.entity.ResultFlag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LabResultDTO {

    private UUID id;

    @NotNull(message = "Lab order ID is required")
    private UUID labOrderId;

    @NotBlank(message = "Result value is required")
    private String resultValue;

    private String unit;

    @NotBlank(message = "Reference range is required")
    private String referenceRange;

    // Computed by service/strategy — don't send from Postman
    private ResultFlag flag;

    @NotNull(message = "Result date is required")
    private LocalDate resultDate;

    // Useful for response
    private String testName;
}