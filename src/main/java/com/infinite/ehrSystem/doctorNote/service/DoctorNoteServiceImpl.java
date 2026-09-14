package com.infinite.ehrSystem.doctorNote.service;

import com.infinite.ehrSystem.doctorNote.dto.DoctorNoteDTO;
import com.infinite.ehrSystem.doctorNote.entity.DoctorNote;
import com.infinite.ehrSystem.doctorNote.repository.DoctorNoteRepository;
import com.infinite.ehrSystem.visit.entity.Visit;
import com.infinite.ehrSystem.visit.repository.VisitRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class DoctorNoteServiceImpl implements DoctorNoteService {

    @Autowired
    private DoctorNoteRepository doctorNoteRepository;

    @Autowired
    private VisitRepository visitRepository;

    @Override
    public DoctorNoteDTO saveDoctorNote(DoctorNoteDTO dto) {

        if (dto.getVisitId() == null) {
            throw new IllegalArgumentException("Visit ID is required");
        }

        if (dto.getDoctorId() == null) {
            throw new IllegalArgumentException("Doctor ID is required");
        }

        if (dto.getNoteType() == null ||
                dto.getNoteType().trim().isEmpty()) {

            throw new IllegalArgumentException("Note type is required");
        }

        if (dto.getNoteText() == null ||
                dto.getNoteText().trim().isEmpty()) {

            throw new IllegalArgumentException("Note text is required");
        }

        if (dto.getNoteText().trim().length() < 10) {

            throw new IllegalArgumentException(
                    "Note text must contain at least 10 characters");
        }

        Visit visit = visitRepository.findById(dto.getVisitId())
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Visit not found with id : "
                                        + dto.getVisitId()));

        DoctorNote doctorNote = new DoctorNote();

        doctorNote.setVisit(visit);
        doctorNote.setDoctorId(dto.getDoctorId());
        doctorNote.setNoteType(dto.getNoteType().trim());
        doctorNote.setNoteText(dto.getNoteText().trim());
        doctorNote.setCreatedAt(LocalDateTime.now());

        DoctorNote savedNote =
                doctorNoteRepository.save(doctorNote);

        return convertToDTO(savedNote);
    }

    @Override
    public List<DoctorNoteDTO> getNotesByVisitId(UUID visitId) {

        if (visitId == null) {
            throw new IllegalArgumentException(
                    "Visit ID is required");
        }

        return doctorNoteRepository.findByVisit_Id(visitId)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public DoctorNoteDTO getDoctorNoteById(UUID id) {

        DoctorNote doctorNote =
                doctorNoteRepository.findById(id)
                        .orElseThrow(() ->
                                new EntityNotFoundException(
                                        "Doctor Note not found"));

        return convertToDTO(doctorNote);
    }

    private DoctorNoteDTO convertToDTO(
            DoctorNote doctorNote) {

        DoctorNoteDTO dto = new DoctorNoteDTO();

        dto.setId(doctorNote.getId());

        if (doctorNote.getVisit() != null) {
            dto.setVisitId(
                    doctorNote.getVisit().getId());
        }

        dto.setDoctorId(doctorNote.getDoctorId());
        dto.setNoteType(doctorNote.getNoteType());
        dto.setNoteText(doctorNote.getNoteText());
        dto.setCreatedAt(doctorNote.getCreatedAt());

        return dto;
    }
}