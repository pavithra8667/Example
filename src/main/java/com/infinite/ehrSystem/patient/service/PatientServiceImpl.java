package com.infinite.ehrSystem.patient.service;

import com.infinite.ehrSystem.patient.dto.PatientDto;
import com.infinite.ehrSystem.patient.entity.Patient;
import com.infinite.ehrSystem.patient.exception.DuplicatePatientException;
import com.infinite.ehrSystem.patient.exception.InvalidPatientDataException;
import com.infinite.ehrSystem.patient.exception.PatientNotFoundException;
import com.infinite.ehrSystem.patient.repository.PatientRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.time.YearMonth;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    private final PatientRepository patientRepository;

    public PatientServiceImpl(
            PatientRepository patientRepository) {

        this.patientRepository = patientRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<PatientDto> searchPatients(String search) {

        List<Patient> patients;

        if (search == null || search.isBlank()) {
            patients = patientRepository.findAll();
        } else {
            patients = patientRepository
                    .findByNameContainingIgnoreCase(search);
        }

        return patients.stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PatientDto getPatient(UUID id) {

        Patient patient = patientRepository.findById(id)
                .orElseThrow(() ->
                        new PatientNotFoundException(
                                "Patient not found"
                        ));

        return convertToDto(patient);
    }

    @Override
    public PatientDto registerPatient(
            PatientDto patientDto) {

        if (patientRepository.existsByPhone(
                patientDto.getPhone())) {

            throw new DuplicatePatientException(
                    "Patient with this phone already exists"
            );
        }

        if (patientDto.getDob().isAfter(
                LocalDate.now())) {

            throw new InvalidPatientDataException(
                    "Date of birth cannot be in the future"
            );
        }

        int age = Period.between(
                patientDto.getDob(),
                LocalDate.now()
        ).getYears();

        if (age < 18 &&
                (patientDto.getGuardianName() == null ||
                        patientDto.getGuardianName().isBlank())) {

            throw new InvalidPatientDataException(
                    "Guardian name is required for patients below 18"
            );
        }

        Patient patient = new Patient();

        patient.setName(patientDto.getName());
        patient.setDob(patientDto.getDob());
        patient.setGender(patientDto.getGender());
        patient.setPhone(patientDto.getPhone());
        patient.setGuardianName(
                patientDto.getGuardianName()
        );

        patient.setPatientCode(
                generatePatientCode()
        );

        Patient savedPatient =
                patientRepository.save(patient);

        return convertToDto(savedPatient);
    }

    @Override
    public PatientDto updatePatient(
            UUID id,
            PatientDto patientDto) {

        Patient patient =
                patientRepository.findById(id)
                        .orElseThrow(() ->
                                new PatientNotFoundException(
                                        "Patient not found"
                                ));

        if (!patient.getPhone()
                .equals(patientDto.getPhone())
                && patientRepository.existsByPhone(
                patientDto.getPhone())) {

            throw new DuplicatePatientException(
                    "Phone number already belongs to another patient"
            );
        }

        if (patientDto.getDob().isAfter(
                LocalDate.now())) {

            throw new InvalidPatientDataException(
                    "Date of birth cannot be in the future"
            );
        }

        int age = Period.between(
                patientDto.getDob(),
                LocalDate.now()
        ).getYears();

        if (age < 18 &&
                (patientDto.getGuardianName() == null ||
                        patientDto.getGuardianName().isBlank())) {

            throw new InvalidPatientDataException(
                    "Guardian name is required for patients below 18"
            );
        }

        patient.setName(patientDto.getName());
        patient.setDob(patientDto.getDob());
        patient.setGender(patientDto.getGender());
        patient.setPhone(patientDto.getPhone());
        patient.setGuardianName(
                patientDto.getGuardianName()
        );

        Patient updatedPatient =
                patientRepository.save(patient);

        return convertToDto(updatedPatient);
    }

    private String generatePatientCode() {

        String prefix =
                "PAT-" +
                        YearMonth.now()
                                .toString()
                                .replace("-", "");

        String code;

        do {
            String randomPart =
                    String.format(
                            "%04d",
                            (int) (Math.random() * 10000)
                    );

            code = prefix + "-" + randomPart;

        } while (
                patientRepository
                        .existsByPatientCode(code)
        );

        return code;
    }

    private PatientDto convertToDto(
            Patient patient) {

        PatientDto dto = new PatientDto();

        dto.setId(patient.getId());
        dto.setPatientCode(patient.getPatientCode());
        dto.setName(patient.getName());
        dto.setDob(patient.getDob());
        dto.setGender(patient.getGender());
        dto.setPhone(patient.getPhone());
        dto.setGuardianName(patient.getGuardianName());

        return dto;
    }
}