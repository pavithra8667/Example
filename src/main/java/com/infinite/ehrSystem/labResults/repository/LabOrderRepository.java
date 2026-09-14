package com.infinite.ehrSystem.labResults.repository;

import com.infinite.ehrSystem.labResults.entity.LabOrder;
import com.infinite.ehrSystem.labResults.entity.LabOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface LabOrderRepository
        extends JpaRepository<LabOrder, UUID> {

    List<LabOrder> findByVisitId(UUID visitId);

    List<LabOrder> findByVisitIdAndStatus(UUID visitId, LabOrderStatus status);
}