package com.infinite.ehrSystem.medicalhistory.service;

import com.infinite.ehrSystem.medicalhistory.dto.MedicalHistoryDto;
import com.infinite.ehrSystem.medicalhistory.entity.MedicalHistory;
import com.infinite.ehrSystem.medicalhistory.exception.InvalidMedicalHistoryException;
import com.infinite.ehrSystem.medicalhistory.exception.MedicalHistoryNotFoundException;
import com.infinite.ehrSystem.medicalhistory.repository.MedicalHistoryRepository;
import com.infinite.ehrSystem.patient.entity.Patient;
import com.infinite.ehrSystem.patient.repository.PatientRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class MedicalHistoryServiceImpl
        implements MedicalHistoryService {

    private final MedicalHistoryRepository
            medicalHistoryRepository;

    private final PatientRepository patientRepository;

    private static final Set<String> ALLOWED_TYPES =
            Set.of(
                    "PAST_ILLNESS",
                    "SURGERY",
                    "FAMILY_HISTORY"
            );

    public MedicalHistoryServiceImpl(
            MedicalHistoryRepository medicalHistoryRepository,
            PatientRepository patientRepository) {

        this.medicalHistoryRepository =
                medicalHistoryRepository;

        this.patientRepository =
                patientRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<MedicalHistoryDto> getHistory(
            UUID patientId) {

        if (!patientRepository.existsById(patientId)) {

            throw new MedicalHistoryNotFoundException(
                    "Patient not found"
            );
        }

        return medicalHistoryRepository
                .findByPatientId(patientId)
                .stream()
                .map(this::convertToDto)
                .toList();
    }

    @Override
    public MedicalHistoryDto addHistory(
            UUID patientId,
            MedicalHistoryDto medicalHistoryDto) {

        Patient patient =
                patientRepository.findById(patientId)
                        .orElseThrow(() ->
                                new MedicalHistoryNotFoundException(
                                        "Patient not found"
                                ));

        if (!ALLOWED_TYPES.contains(
                medicalHistoryDto.getHistoryType())) {

            throw new InvalidMedicalHistoryException(
                    "Invalid medical history type"
            );
        }

        if (medicalHistoryDto.getConditionName() == null ||
                medicalHistoryDto.getConditionName().isBlank()) {

            throw new InvalidMedicalHistoryException(
                    "Condition name is required"
            );
        }

        if (medicalHistoryDto.getDiagnosedYear() != null &&
                medicalHistoryDto.getDiagnosedYear()
                        > Year.now().getValue()) {

            throw new InvalidMedicalHistoryException(
                    "Diagnosed year cannot be in the future"
            );
        }

        MedicalHistory history =
                new MedicalHistory();

        history.setPatient(patient);

        history.setHistoryType(
                medicalHistoryDto.getHistoryType()
        );

        history.setConditionName(
                medicalHistoryDto.getConditionName()
        );

        history.setDiagnosedYear(
                medicalHistoryDto.getDiagnosedYear()
        );

        history.setNotes(
                medicalHistoryDto.getNotes()
        );

        MedicalHistory savedHistory =
                medicalHistoryRepository.save(history);

        return convertToDto(savedHistory);
    }

    private MedicalHistoryDto convertToDto(
            MedicalHistory history) {

        MedicalHistoryDto dto =
                new MedicalHistoryDto();

        dto.setId(history.getId());
        dto.setPatientId(history.getPatient().getId());
        dto.setHistoryType(history.getHistoryType());
        dto.setConditionName(history.getConditionName());
        dto.setDiagnosedYear(history.getDiagnosedYear());
        dto.setNotes(history.getNotes());

        return dto;
    }
}