package com.infinite.ehrSystem.labResults.service;

import com.infinite.ehrSystem.labResults.dto.LabOrderDTO;
import com.infinite.ehrSystem.labResults.entity.LabOrderStatus;

import java.util.List;
import java.util.UUID;

public interface LabOrderService {

    LabOrderDTO createLabOrder(LabOrderDTO dto);

    LabOrderDTO getLabOrderById(UUID id);

    List<LabOrderDTO> getLabOrdersByVisitId(UUID visitId);

    List<LabOrderDTO> getLabOrdersByVisitIdAndStatus(
            UUID visitId,
            LabOrderStatus status
    );

    LabOrderDTO updateLabOrder(
            UUID id,
            LabOrderDTO dto
    );

    void deleteLabOrder(UUID id);
}