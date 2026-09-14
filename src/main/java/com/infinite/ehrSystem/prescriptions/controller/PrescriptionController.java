package com.infinite.ehrSystem.prescriptions.controller;

import com.infinite.ehrSystem.prescriptions.dto.PrescriptionDTO;
import com.infinite.ehrSystem.prescriptions.service.PrescriptionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    private final PrescriptionService prescriptionService;

    public PrescriptionController(
            PrescriptionService prescriptionService) {

        this.prescriptionService = prescriptionService;
    }

    @PostMapping
    public ResponseEntity<PrescriptionDTO> createPrescription(
            @Valid @RequestBody PrescriptionDTO dto) {

        PrescriptionDTO saved =
                prescriptionService.createPrescription(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    @GetMapping("/{prescriptionId}")
    public ResponseEntity<PrescriptionDTO> getPrescriptionById(
            @PathVariable UUID prescriptionId) {

        return ResponseEntity.ok(
                prescriptionService.getPrescriptionById(
                        prescriptionId
                )
        );
    }

    @GetMapping("/visit/{visitId}")
    public ResponseEntity<List<PrescriptionDTO>>
    getPrescriptionsByVisitId(
            @PathVariable UUID visitId) {

        return ResponseEntity.ok(
                prescriptionService
                        .getPrescriptionsByVisitId(visitId)
        );
    }

    @PutMapping("/{prescriptionId}")
    public ResponseEntity<PrescriptionDTO> updatePrescription(
            @PathVariable UUID prescriptionId,
            @Valid @RequestBody PrescriptionDTO dto) {

        return ResponseEntity.ok(
                prescriptionService.updatePrescription(
                        prescriptionId,
                        dto
                )
        );
    }

    @DeleteMapping("/{prescriptionId}")
    public ResponseEntity<Void> deletePrescription(
            @PathVariable UUID prescriptionId) {

        prescriptionService.deletePrescription(
                prescriptionId
        );

        return ResponseEntity.noContent().build();
    }
}