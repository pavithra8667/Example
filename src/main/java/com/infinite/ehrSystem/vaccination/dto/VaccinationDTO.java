package com.infinite.ehrSystem.vaccination.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VaccinationDTO {

    private UUID id;

    @NotNull(message = "Patient ID is required")
    private UUID patientId;

    @NotBlank(message = "Vaccine name is required")
    private String vaccineName;

    private Integer doseNumber;

    @NotNull(message = "Administered date is required")
    @PastOrPresent(message = "Administered date cannot be in the future")
    private LocalDate administeredDate;

    private String administeredBy;

    private LocalDate nextDueDate;


}