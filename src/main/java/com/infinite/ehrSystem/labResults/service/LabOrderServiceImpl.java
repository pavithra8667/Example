package com.infinite.ehrSystem.labResults.service;

import com.infinite.ehrSystem.exception.LabOrderNotFoundException;
import com.infinite.ehrSystem.labResults.dto.LabOrderDTO;
import com.infinite.ehrSystem.labResults.entity.LabOrder;
import com.infinite.ehrSystem.labResults.entity.LabOrderStatus;
import com.infinite.ehrSystem.labResults.repository.LabOrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class LabOrderServiceImpl implements LabOrderService {

    private final LabOrderRepository labOrderRepository;

    public LabOrderServiceImpl(
            LabOrderRepository labOrderRepository) {

        this.labOrderRepository = labOrderRepository;
    }

    @Override
    public LabOrderDTO createLabOrder(LabOrderDTO dto) {

        LabOrder labOrder = LabOrder.builder()
                .visitId(dto.getVisitId())
                .testName(dto.getTestName())
                .status(dto.getStatus() != null ? dto.getStatus() : LabOrderStatus.ORDERED)
                .build();

        LabOrder savedOrder =
                labOrderRepository.save(labOrder);

        return mapToDTO(savedOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public LabOrderDTO getLabOrderById(UUID id) {

        LabOrder labOrder =
                labOrderRepository.findById(id).orElseThrow(() ->
                                new LabOrderNotFoundException("Lab order not found with id: " + id));
        return mapToDTO(labOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LabOrderDTO> getLabOrdersByVisitId(UUID visitId) {
        return labOrderRepository
                .findByVisitId(visitId)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<LabOrderDTO> getLabOrdersByVisitIdAndStatus(UUID visitId, LabOrderStatus status) {
        return labOrderRepository
                .findByVisitIdAndStatus(visitId, status)
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public LabOrderDTO updateLabOrder(UUID id, LabOrderDTO dto) {
        LabOrder labOrder = labOrderRepository.findById(id).orElseThrow(() ->
                        new LabOrderNotFoundException("Lab order not found with id: " + id));
        labOrder.setVisitId(dto.getVisitId());
        labOrder.setTestName(dto.getTestName());

        if (dto.getStatus() != null)
        {
            labOrder.setStatus(dto.getStatus());
        }

        LabOrder updatedOrder =
                labOrderRepository.save(labOrder);

        return mapToDTO(updatedOrder);
    }

    @Override
    public void deleteLabOrder(UUID id) {

        if (!labOrderRepository.existsById(id)) {
            throw new LabOrderNotFoundException(
                    "Lab order not found with id: " + id
            );
        }

        labOrderRepository.deleteById(id);
    }

    private LabOrderDTO mapToDTO(LabOrder labOrder) {

        return LabOrderDTO.builder()
                .id(labOrder.getId())
                .visitId(labOrder.getVisitId())
                .testName(labOrder.getTestName())
                .status(labOrder.getStatus())
                .build();
    }
}