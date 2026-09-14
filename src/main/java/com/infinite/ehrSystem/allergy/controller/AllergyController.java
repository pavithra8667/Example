package com.infinite.ehrSystem.allergy.controller;

import com.infinite.ehrSystem.allergy.dto.AllergyDTO;
import com.infinite.ehrSystem.allergy.service.AllergyService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/patients/{patientId}/allergies")
public class AllergyController {

    private final AllergyService allergyService;

    public AllergyController(AllergyService allergyService) {
        this.allergyService = allergyService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<List<AllergyDTO>> getAllergies(
            @PathVariable UUID patientId) {

        List<AllergyDTO> allergies =
                allergyService.getAllergies(patientId);

        return ResponseEntity.ok(allergies);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    public ResponseEntity<AllergyDTO> addAllergy(
            @PathVariable UUID patientId,
            @Valid @RequestBody AllergyDTO allergyDTO) {

        AllergyDTO createdAllergy =
                allergyService.addAllergy(
                        patientId,
                        allergyDTO
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdAllergy);
    }
}