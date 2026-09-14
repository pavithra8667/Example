package com.infinite.ehrSystem.patientPortal.dto;

import com.infinite.ehrSystem.allergy.dto.AllergyDTO;
import com.infinite.ehrSystem.diagnosis.dto.DiagnosisDTO;
import com.infinite.ehrSystem.labResults.dto.LabResultDTO;
import com.infinite.ehrSystem.medicalhistory.dto.MedicalHistoryDto;
import com.infinite.ehrSystem.patient.dto.PatientDto;
import com.infinite.ehrSystem.prescriptions.dto.PrescriptionDTO;
import com.infinite.ehrSystem.prescriptions.dto.PrescriptionDTO;
import com.infinite.ehrSystem.vaccination.dto.VaccinationDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientPortalDTO {

    private PatientDto patient;

    private List<MedicalHistoryDto> medicalHistories;

    private List<AllergyDTO> allergies;

    private List<DiagnosisDTO> diagnoses;

    private List<LabResultDTO> labResults;

    private List<PrescriptionDTO> prescriptions;

    private List<VaccinationDTO> vaccinations;
}