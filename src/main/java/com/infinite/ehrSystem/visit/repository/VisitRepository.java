package com.infinite.ehrSystem.visit.repository;

import com.infinite.ehrSystem.visit.entity.Visit;
import com.infinite.ehrSystem.visit.entity.VisitStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VisitRepository extends JpaRepository<Visit, UUID> {

    boolean existsByVisitCode(String visitCode);

    List<Visit> findByPatientId(UUID patientId);

    List<Visit> findByDoctorId(UUID doctorId);

    List<Visit> findByPatientIdAndStatus(
            UUID patientId,
            VisitStatus status
    );
}