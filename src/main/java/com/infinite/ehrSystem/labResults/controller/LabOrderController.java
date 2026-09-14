package com.infinite.ehrSystem.labResults.controller;

import com.infinite.ehrSystem.labResults.dto.LabOrderDTO;
import com.infinite.ehrSystem.labResults.entity.LabOrderStatus;
import com.infinite.ehrSystem.labResults.service.LabOrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/lab-orders")
public class LabOrderController {

    private final LabOrderService labOrderService;

    public LabOrderController(
            LabOrderService labOrderService) {

        this.labOrderService = labOrderService;
    }

    @PostMapping
    public ResponseEntity<LabOrderDTO> createLabOrder(
            @Valid @RequestBody LabOrderDTO dto) {

        LabOrderDTO createdOrder =
                labOrderService.createLabOrder(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdOrder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LabOrderDTO> getLabOrderById(
            @PathVariable UUID id) {

        return ResponseEntity.ok(
                labOrderService.getLabOrderById(id)
        );
    }

    @GetMapping("/visit/{visitId}")
    public ResponseEntity<List<LabOrderDTO>>
    getLabOrdersByVisitId(
            @PathVariable UUID visitId) {

        return ResponseEntity.ok(labOrderService.getLabOrdersByVisitId(visitId));
    }

    @GetMapping("/visit/{visitId}/status/{status}")
    public ResponseEntity<List<LabOrderDTO>>
    getLabOrdersByVisitIdAndStatus(
            @PathVariable UUID visitId,
            @PathVariable LabOrderStatus status) {

        return ResponseEntity.ok(labOrderService.getLabOrdersByVisitIdAndStatus(visitId, status));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LabOrderDTO> updateLabOrder(
            @PathVariable UUID id,
            @Valid @RequestBody LabOrderDTO dto) {

        return ResponseEntity.ok(labOrderService.updateLabOrder(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLabOrder(
            @PathVariable UUID id) {

        labOrderService.deleteLabOrder(id);

        return ResponseEntity.noContent().build();
    }
}