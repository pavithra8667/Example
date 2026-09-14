package com.infinite.ehrSystem.patient.controller;

import com.infinite.ehrSystem.patient.dto.PatientDto;
import com.infinite.ehrSystem.patient.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR','LAB_TECH')")
    public ResponseEntity<List<PatientDto>> getPatients(
            @RequestParam(required = false) String search) {

        List<PatientDto> patients =
                patientService.searchPatients(search);

        return ResponseEntity.ok(patients);
    }

    @PostMapping("/register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PatientDto> registerPatient(
            @Valid @RequestBody PatientDto patientDto) {

        PatientDto createdPatient =
                patientService.registerPatient(patientDto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdPatient);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR','LAB_TECH')")
    public ResponseEntity<PatientDto> getPatient(
            @PathVariable UUID id) {

        PatientDto patient =
                patientService.getPatient(id);

        return ResponseEntity.ok(patient);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PatientDto> updatePatient(
            @PathVariable UUID id,
            @Valid @RequestBody PatientDto patientDto) {

        PatientDto updatedPatient =
                patientService.updatePatient(
                        id,
                        patientDto
                );

        return ResponseEntity.ok(updatedPatient);
    }

    @GetMapping("/test-auth")
    public String testAuth(
            Authentication authentication,
            @AuthenticationPrincipal Jwt jwt,
            @RequestHeader(
                    value = "Authorization",
                    required = false
            ) String authHeader) {

        return "AUTH HEADER = "
                + authHeader
                + " | AUTHENTICATION = "
                + authentication
                + " | JWT = "
                + jwt;
    }

    @GetMapping("/token-debug")
    public String tokenDebug(
            @RequestHeader(
                    value = "Authorization",
                    required = false
            ) String authHeader) {

        return authHeader;
    }
}