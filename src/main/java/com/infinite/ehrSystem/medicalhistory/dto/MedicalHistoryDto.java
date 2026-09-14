package com.infinite.ehrSystem.medicalhistory.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class MedicalHistoryDto {

    private UUID id;

    private UUID patientId;

    @NotBlank(message = "History type is required")
    @Pattern(
            regexp = "PAST_ILLNESS|SURGERY|FAMILY_HISTORY",
            message = "Invalid history type"
    )
    private String historyType;

    @NotBlank(message = "Condition name is required")
    private String conditionName;

    @Min(
            value = 1900,
            message = "Invalid diagnosed year"
    )
    private Integer diagnosedYear;

    private String notes;
}