package com.infinite.ehrSystem.visit.service;

import com.infinite.ehrSystem.exception.VisitAlreadyClosedException;
import com.infinite.ehrSystem.exception.VisitNotFoundException;
import com.infinite.ehrSystem.auth.repository.UserRepository;
import com.infinite.ehrSystem.patient.repository.PatientRepository;
import com.infinite.ehrSystem.visit.dto.VisitDTO;
import com.infinite.ehrSystem.visit.entity.Visit;
import com.infinite.ehrSystem.visit.entity.VisitStatus;
import com.infinite.ehrSystem.visit.repository.VisitRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {

    private final VisitRepository visitRepository;
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public VisitDTO createVisit(VisitDTO visitDTO) {

        Visit visit = new Visit();

        visit.setVisitCode(generateVisitCode());

        visit.setPatientId(visitDTO.getPatientId());

        visit.setDoctorId(visitDTO.getDoctorId());

        visit.setVisitDate(
                visitDTO.getVisitDate() != null
                        ? visitDTO.getVisitDate()
                        : LocalDateTime.now()
        );

        // New visit should always start as OPEN
        visit.setStatus(VisitStatus.OPEN);

        Visit savedVisit = visitRepository.save(visit);

        return convertToDTO(savedVisit);
    }

    @Override
    @Transactional(readOnly = true)
    public VisitDTO getVisitById(UUID id) {

        Visit visit = visitRepository.findById(id)
                .orElseThrow(() ->
                        new VisitNotFoundException(
                                "Visit not found with id: " + id
                        )
                );

        return convertToDTO(visit);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VisitDTO> getVisitsByPatient(UUID patientId) {

        return visitRepository
                .findByPatientId(patientId)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<VisitDTO> getVisitsByDoctor(UUID doctorId) {

        return visitRepository
                .findByDoctorId(doctorId)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<VisitDTO> getAllVisits() {

        return visitRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    @Transactional
    public VisitDTO closeVisit(UUID id) {

        Visit visit = visitRepository.findById(id)
                .orElseThrow(() ->
                        new VisitNotFoundException(
                                "Visit not found with id: " + id
                        )
                );

        if (visit.getStatus() == VisitStatus.CLOSED) {
            throw new VisitAlreadyClosedException(
                    "Visit is already closed"
            );
        }

        visit.setStatus(VisitStatus.CLOSED);

        Visit updatedVisit = visitRepository.save(visit);

        return convertToDTO(updatedVisit);
    }

    private String generateVisitCode() {

        return "VIS-" +
                UUID.randomUUID()
                        .toString()
                        .substring(0, 8)
                        .toUpperCase();
    }

    private VisitDTO convertToDTO(Visit visit) {

        VisitDTO dto = new VisitDTO();

        dto.setId(visit.getId());

        dto.setVisitCode(visit.getVisitCode());

        dto.setPatientId(visit.getPatientId());

        dto.setDoctorId(visit.getDoctorId());

        dto.setVisitDate(visit.getVisitDate());

        dto.setStatus(visit.getStatus());

        if (visit.getPatientId() != null) {

            patientRepository
                    .findById(visit.getPatientId())
                    .ifPresent(patient -> {

                        dto.setPatientName(
                                patient.getName()
                        );

                        dto.setPatientCode(
                                patient.getPatientCode()
                        );
                    });
        }

        if (visit.getDoctorId() != null) {

            userRepository.findById(visit.getDoctorId())
                    .ifPresent(doctor -> dto.setDoctorName(
                            doctor.getUsername()
                    ));
        }

        dto.setCreatedBy(visit.getCreatedBy());

        dto.setCreatedOn(visit.getCreatedOn());

        dto.setUpdatedBy(visit.getUpdatedBy());

        dto.setUpdatedOn(visit.getUpdatedOn());

        return dto;
    }
}
