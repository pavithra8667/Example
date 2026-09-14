package com.infinite.ehrSystem.medicalhistory.service;

import com.infinite.ehrSystem.medicalhistory.dto.MedicalHistoryDto;

import java.util.List;
import java.util.UUID;

public interface MedicalHistoryService {

    List<MedicalHistoryDto> getHistory(
            UUID patientId
    );

    MedicalHistoryDto addHistory(
            UUID patientId,
            MedicalHistoryDto medicalHistoryDto
    );
}