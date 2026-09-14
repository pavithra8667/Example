package com.infinite.ehrSystem.medicalhistory.controller;
import com.infinite.ehrSystem.medicalhistory.dto.MedicalHistoryDto;
import com.infinite.ehrSystem.medicalhistory.service.MedicalHistoryService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/patients/{patientId}/history")
public class MedicalHistoryController {

    private final MedicalHistoryService medicalHistoryService;

    public MedicalHistoryController(
            MedicalHistoryService medicalHistoryService) {

        this.medicalHistoryService = medicalHistoryService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<List<MedicalHistoryDto>> getHistory(
            @PathVariable UUID patientId) {

        List<MedicalHistoryDto> history =
                medicalHistoryService.getHistory(patientId);

        return ResponseEntity.ok(history);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<MedicalHistoryDto> addHistory(
            @PathVariable UUID patientId,
            @Valid @RequestBody MedicalHistoryDto medicalHistoryDto) {

        MedicalHistoryDto createdHistory =
                medicalHistoryService.addHistory(
                        patientId,
                        medicalHistoryDto
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdHistory);
    }
}