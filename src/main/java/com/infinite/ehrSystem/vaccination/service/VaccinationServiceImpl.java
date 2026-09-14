package com.infinite.ehrSystem.vaccination.service;

import com.infinite.ehrSystem.patient.entity.Patient;
import com.infinite.ehrSystem.patient.repository.PatientRepository;
import com.infinite.ehrSystem.vaccination.dto.VaccinationDTO;
import com.infinite.ehrSystem.vaccination.entity.Vaccination;
import com.infinite.ehrSystem.vaccination.repository.VaccinationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class VaccinationServiceImpl implements VaccinationService {

    @Autowired
    private VaccinationRepository vaccinationRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    @Transactional
    public VaccinationDTO saveVaccination(VaccinationDTO dto) {

        if (dto.getPatientId() == null) {
            throw new IllegalArgumentException("Patient ID is required");
        }

        if (dto.getVaccineName() == null ||
                dto.getVaccineName().trim().isEmpty()) {
            throw new IllegalArgumentException("Vaccine name is required");
        }

        if (dto.getAdministeredDate() == null) {
            throw new IllegalArgumentException("Administered date is required");
        }

        if (dto.getAdministeredDate().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(
                    "Administered date cannot be in the future");
        }

        String vaccineName = dto.getVaccineName().trim();

        Patient patient = patientRepository
                .findById(dto.getPatientId())
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Patient not found with ID: "
                                        + dto.getPatientId()));

        List<Vaccination> previousDoses =vaccinationRepository
                        .findByPatientIdAndVaccineNameOrderByDoseNumberDesc(
                                dto.getPatientId(),
                                vaccineName);

        int nextDoseNumber;

        if (previousDoses.isEmpty()) {
            nextDoseNumber = 1;
        } else {
            nextDoseNumber =
                    previousDoses.get(0).getDoseNumber() + 1;
        }

        Vaccination vaccination = new Vaccination();

        vaccination.setPatient(patient);
        vaccination.setVaccineName(vaccineName);
        vaccination.setDoseNumber(nextDoseNumber);
        vaccination.setAdministeredDate(dto.getAdministeredDate());
        vaccination.setAdministeredBy(dto.getAdministeredBy());

        vaccination.setNextDueDate(
                calculateNextDueDate(
                        vaccineName,
                        dto.getAdministeredDate(),
                        nextDoseNumber));

        Vaccination saved =vaccinationRepository.save(vaccination);

        return convertToDTO(saved);
    }

    @Override
    public List<VaccinationDTO> getVaccinationsByPatientId(UUID patientId) {

        if (patientId == null) {
            throw new IllegalArgumentException(
                    "Patient ID is required");
        }

        return vaccinationRepository
                .findByPatientId(patientId)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public VaccinationDTO getVaccinationById(UUID vaccinationId) {

        Vaccination vaccination =vaccinationRepository.findById(vaccinationId)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Vaccination not found with ID: "
                                                + vaccinationId));

        return convertToDTO(vaccination);
    }

    @Override
    @Transactional
    public VaccinationDTO updateVaccination(UUID vaccinationId,VaccinationDTO dto) {

        Vaccination vaccination =vaccinationRepository.findById(vaccinationId)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Vaccination not found with ID: "
                                                + vaccinationId));

        if (dto.getVaccineName() != null &&
                !dto.getVaccineName().trim().isEmpty()) {

            vaccination.setVaccineName(
                    dto.getVaccineName().trim());
        }

        if (dto.getAdministeredDate() != null) {

            vaccination.setAdministeredDate(
                    dto.getAdministeredDate());

            vaccination.setNextDueDate(
                    calculateNextDueDate(
                            vaccination.getVaccineName(),
                            dto.getAdministeredDate(),
                            vaccination.getDoseNumber()));
        }

        vaccination.setAdministeredBy(
                dto.getAdministeredBy());

        Vaccination updated =vaccinationRepository.save(vaccination);

        return convertToDTO(updated);
    }

    @Override
    @Transactional
    public void deleteVaccination(UUID vaccinationId) {

        Vaccination vaccination =vaccinationRepository.findById(vaccinationId)
                        .orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Vaccination not found with ID: "
                                                + vaccinationId));

        vaccinationRepository.delete(vaccination);
    }

    private LocalDate calculateNextDueDate(String vaccineName,LocalDate administeredDate,int doseNumber) {

        String vaccine = vaccineName.trim().toUpperCase();

        switch (vaccine) {

            case "BCG":
                return null;

            case "HEPATITIS B":
            case "HEPATITIS-B":
                return doseNumber >= 3
                        ? null
                        : administeredDate.plusMonths(1);

            case "DPT":
            case "DTAP":
                return doseNumber >= 3
                        ? null
                        : administeredDate.plusMonths(1);

            case "POLIO":
            case "OPV":
            case "IPV":
                return doseNumber >= 3
                        ? null
                        : administeredDate.plusMonths(1);

            case "MMR":
                return doseNumber >= 2
                        ? null
                        : administeredDate.plusMonths(1);

            case "INFLUENZA":
            case "FLU":
                return administeredDate.plusYears(1);

            case "COVID":
            case "COVID-19":
                return administeredDate.plusYears(1);

            default:
                return null;
        }
    }

    private VaccinationDTO convertToDTO(Vaccination entity) {

        VaccinationDTO dto = new VaccinationDTO();

        dto.setId(entity.getId());

        if (entity.getPatient() != null) {
            dto.setPatientId(
                    entity.getPatient().getId());
        }

        dto.setVaccineName(entity.getVaccineName());
        dto.setDoseNumber(entity.getDoseNumber());
        dto.setAdministeredDate(entity.getAdministeredDate());
        dto.setAdministeredBy(entity.getAdministeredBy());
        dto.setNextDueDate(entity.getNextDueDate());

        return dto;
    }
}