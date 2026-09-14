package com.infinite.ehrSystem.patientPortal.service;
import com.infinite.ehrSystem.allergy.service.AllergyService;
import com.infinite.ehrSystem.diagnosis.service.DiagnosisService;
import com.infinite.ehrSystem.labResults.service.LabResultService;
import com.infinite.ehrSystem.medicalhistory.service.MedicalHistoryService;
import com.infinite.ehrSystem.patient.repository.PatientRepository;
import com.infinite.ehrSystem.patientPortal.dto.PatientPortalDTO;
import com.infinite.ehrSystem.prescriptions.service.PrescriptionService;
import com.infinite.ehrSystem.vaccination.service.VaccinationService;
import org.springframework.stereotype.Service;
import com.infinite.ehrSystem.prescriptions.service.PrescriptionServiceImpl;

@Service
public class PatientPortalServiceImpl implements PatientPortalService {

    PatientRepository patientRepository;

    MedicalHistoryService medicalHistoryService;

    AllergyService allergyService;
    DiagnosisService diagnosisService;
    LabResultService labResultService;
    PrescriptionServiceImpl prescriptionService;
    VaccinationService vaccinationService;

    @Override
    public PatientPortalDTO getPatientPortal(String username) {
        return null;
    }
}