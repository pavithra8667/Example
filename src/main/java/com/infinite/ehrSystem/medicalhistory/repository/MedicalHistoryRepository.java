package com.infinite.ehrSystem.medicalhistory.repository;
import com.infinite.ehrSystem.medicalhistory.entity.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;
public interface  MedicalHistoryRepository extends JpaRepository<MedicalHistory, UUID> {

    List<MedicalHistory> findByPatientId(UUID  patientId);
}
