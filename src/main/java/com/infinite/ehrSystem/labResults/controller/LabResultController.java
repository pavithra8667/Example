package com.infinite.ehrSystem.labResults.controller;

import com.infinite.ehrSystem.labResults.dto.LabResultDTO;
import com.infinite.ehrSystem.labResults.service.LabResultService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/lab-results")
public class LabResultController {

    private final LabResultService labResultService;

    public LabResultController(LabResultService labResultService) {
        this.labResultService = labResultService;
    }

    @PostMapping
    public ResponseEntity<LabResultDTO> createLabResult(@Valid @RequestBody LabResultDTO dto) {
        LabResultDTO createdResult = labResultService.createLabResult(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdResult);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LabResultDTO> getLabResultById(@PathVariable UUID id) {
        return ResponseEntity.ok(labResultService.getLabResultById(id));
    }

    @GetMapping("/lab-order/{labOrderId}")
    public ResponseEntity<LabResultDTO>
    getLabResultByLabOrderId(@PathVariable UUID labOrderId) {
        return ResponseEntity.ok(labResultService.getLabResultByLabOrderId(labOrderId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LabResultDTO> updateLabResult(
            @PathVariable UUID id, @Valid @RequestBody LabResultDTO dto) {
        return ResponseEntity.ok(labResultService.updateLabResult(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLabResult(@PathVariable UUID id) {
        labResultService.deleteLabResult(id);
        return ResponseEntity.noContent().build();
    }
}