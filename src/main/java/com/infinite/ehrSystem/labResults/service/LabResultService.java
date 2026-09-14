package com.infinite.ehrSystem.labResults.service;

import com.infinite.ehrSystem.labResults.dto.LabResultDTO;

import java.util.UUID;

public interface LabResultService {

    LabResultDTO createLabResult(LabResultDTO dto);

    LabResultDTO getLabResultById(UUID id);

    LabResultDTO getLabResultByLabOrderId(UUID labOrderId);

    LabResultDTO updateLabResult(
            UUID id,
            LabResultDTO dto
    );

    void deleteLabResult(UUID id);
}