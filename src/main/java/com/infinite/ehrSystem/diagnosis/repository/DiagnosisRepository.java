package com.infinite.ehrSystem.diagnosis.repository;

import com.infinite.ehrSystem.diagnosis.entity.Diagnosis;
import com.infinite.ehrSystem.diagnosis.entity.DiagnosisType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DiagnosisRepository
        extends JpaRepository<Diagnosis, UUID> {

    List<Diagnosis> findByVisitId(UUID visitId);

    boolean existsByVisitIdAndDiagnosisType(UUID visitId, DiagnosisType diagnosisType
    );

    boolean existsByVisitId(UUID visitId);
}