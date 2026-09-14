package com.infinite.ehrSystem.patient.service;

import com.infinite.ehrSystem.patient.dto.PatientDto;
import java.util.List;
import java.util.UUID;

public interface PatientService {

    List<PatientDto> searchPatients(String search);

    PatientDto getPatient(UUID id);

    PatientDto registerPatient(PatientDto patientDTO);

    PatientDto updatePatient(
            UUID id,
            PatientDto patientDTO
    );
}