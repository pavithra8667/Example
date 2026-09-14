package com.infinite.ehrSystem.prescriptions.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import com.infinite.ehrSystem.prescriptions.dto.PrescriptionItemDTO;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionDTO {

    private UUID prescriptionId;

    @NotNull(message = "Visit ID is required")
    private UUID visitId;

    @Valid
    private List<PrescriptionItemDTO> items;
}