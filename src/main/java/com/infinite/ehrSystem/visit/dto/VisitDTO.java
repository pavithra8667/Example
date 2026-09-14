package com.infinite.ehrSystem.visit.dto;

import com.infinite.ehrSystem.visit.entity.VisitStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VisitDTO {

    private UUID id;

    private String visitCode;

    private UUID patientId;

    private UUID doctorId;

    private String doctorName;

    private LocalDateTime visitDate;

    private VisitStatus status;

    private String patientName;

    private String patientCode;

    private String createdBy;

    private LocalDateTime createdOn;

    private String updatedBy;

    private LocalDateTime updatedOn;
}
