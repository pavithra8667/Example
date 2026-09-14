package com.infinite.ehrSystem.doctorNote.controller;

import com.infinite.ehrSystem.doctorNote.dto.DoctorNoteDTO;
import com.infinite.ehrSystem.doctorNote.service.DoctorNoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/doctor-notes")
@RequiredArgsConstructor
public class DoctorNoteController {

    private final DoctorNoteService doctorNoteService;

    @GetMapping
    public List<DoctorNoteDTO> doctorNoteList(
            @RequestParam UUID visitId) {

        return doctorNoteService.getNotesByVisitId(visitId);
    }

    @GetMapping("/form")
    public String doctorNoteForm(
            @RequestParam UUID visitId) {

        return "Doctor note form available for visit: " + visitId;
    }

    @PostMapping("/save")
    public DoctorNoteDTO saveDoctorNote(
            @RequestBody DoctorNoteDTO doctorNoteDTO) {

        return doctorNoteService.saveDoctorNote(
                doctorNoteDTO);
    }
}