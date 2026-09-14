package com.infinite.ehrSystem.diagnosis.controller;
import com.infinite.ehrSystem.diagnosis.dto.DiagnosisDTO;
import com.infinite.ehrSystem.diagnosis.service.DiagnosisService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
@RestController
@RequiredArgsConstructor
public class DiagnosisController {
    private final DiagnosisService diagnosisService;

    @PostMapping("/api/visits/{visitId}/diagnoses")
    public ResponseEntity<DiagnosisDTO> createDiagnosis(
            @PathVariable UUID visitId,
            @Valid @RequestBody DiagnosisDTO diagnosisDTO) {
        DiagnosisDTO createdDiagnosis = diagnosisService.createDiagnosis(visitId, diagnosisDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDiagnosis);
    }

    @GetMapping("/api/visits/{visitId}/diagnoses")
    public ResponseEntity<List<DiagnosisDTO>> getDiagnosesByVisit(@PathVariable UUID visitId)
    {
        return ResponseEntity.ok(diagnosisService.getDiagnosesByVisit(visitId));
    }

    @GetMapping("/api/diagnoses/{id}")
    public ResponseEntity<DiagnosisDTO> getDiagnosisById(@PathVariable UUID id) {
        return ResponseEntity.ok(diagnosisService.getDiagnosisById(id));
    }

    @DeleteMapping("/api/diagnoses/{id}")
    public ResponseEntity<Void> deleteDiagnosis(@PathVariable UUID id) {
        diagnosisService.deleteDiagnosis(id);
        return ResponseEntity.noContent().build();
    }
}