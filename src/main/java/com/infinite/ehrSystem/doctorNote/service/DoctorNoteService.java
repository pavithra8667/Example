package com.infinite.ehrSystem.doctorNote.service;

import com.infinite.ehrSystem.doctorNote.dto.DoctorNoteDTO;

import java.util.List;
import java.util.UUID;

public interface DoctorNoteService {

    DoctorNoteDTO saveDoctorNote(DoctorNoteDTO doctorNoteDTO);

    List<DoctorNoteDTO> getNotesByVisitId(UUID visitId);

    DoctorNoteDTO getDoctorNoteById(UUID id);
}