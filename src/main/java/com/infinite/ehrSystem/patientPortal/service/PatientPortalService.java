package com.infinite.ehrSystem.patientPortal.service;

import com.infinite.ehrSystem.patientPortal.dto.PatientPortalDTO;

public interface PatientPortalService {

    PatientPortalDTO getPatientPortal(String username);
}