package com.infinite.ehrSystem.visit.controller;

import com.infinite.ehrSystem.visit.dto.VisitDTO;
import com.infinite.ehrSystem.visit.service.VisitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/visits")
@RequiredArgsConstructor
public class VisitController {

    private final VisitService visitService;

    @PostMapping
    public ResponseEntity<VisitDTO> createVisit(
            @Valid @RequestBody VisitDTO visitDTO) {

        VisitDTO createdVisit =
                visitService.createVisit(visitDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdVisit);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisitDTO> getVisitById(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                visitService.getVisitById(id)
        );
    }

    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<VisitDTO>> getVisitsByPatient(
            @PathVariable UUID patientId) {

        return ResponseEntity.ok(
                visitService.getVisitsByPatient(patientId)
        );
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<VisitDTO>> getVisitsByDoctor(
            @PathVariable UUID doctorId) {

        return ResponseEntity.ok(
                visitService.getVisitsByDoctor(doctorId)
        );
    }
    @GetMapping
    public ResponseEntity<List<VisitDTO>>
    getAllVisits() {

        return ResponseEntity.ok(
                visitService.getAllVisits()
        );
    }
    @PutMapping("/{id}/close")
    public ResponseEntity<VisitDTO> closeVisit(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                visitService.closeVisit(id)
        );
    }
}