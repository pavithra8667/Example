package com.infinite.ehrSystem.diagnosis.service;
import com.infinite.ehrSystem.diagnosis.dto.DiagnosisDTO;
import java.util.List;
import java.util.UUID;

public interface DiagnosisService {
    DiagnosisDTO createDiagnosis(UUID visitId, DiagnosisDTO diagnosisDTO);
    DiagnosisDTO getDiagnosisById(UUID id);
    List<DiagnosisDTO> getDiagnosesByVisit(UUID visitId);
    void deleteDiagnosis(UUID id);
}