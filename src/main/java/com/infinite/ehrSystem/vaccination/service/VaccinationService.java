package com.infinite.ehrSystem.vaccination.service;

import com.infinite.ehrSystem.vaccination.dto.VaccinationDTO;

import java.util.List;
import java.util.UUID;

public interface VaccinationService {

    VaccinationDTO saveVaccination(VaccinationDTO dto);
    List<VaccinationDTO> getVaccinationsByPatientId(UUID patientId);
    VaccinationDTO getVaccinationById(UUID vaccinationId);
    VaccinationDTO updateVaccination(UUID vaccinationId,VaccinationDTO dto);
    void deleteVaccination(UUID vaccinationId);
}