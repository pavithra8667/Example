package com.infinite.ehrSystem.prescriptions.repository;

import com.infinite.ehrSystem.prescriptions.entity.PrescriptionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PrescriptionItemRepository
        extends JpaRepository<PrescriptionItem, UUID> {
}