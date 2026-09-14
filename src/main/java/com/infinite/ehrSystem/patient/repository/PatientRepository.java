package com.infinite.ehrSystem.patient.repository;
import com.infinite.ehrSystem.patient.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
public interface  PatientRepository extends JpaRepository<Patient, UUID>{
    boolean existsByPhone(String phone);
    boolean existsByPatientCode(String patientCode);

    Optional<Patient> findByPhone(String phone);
    List<Patient> findByNameContainingIgnoreCase(String name);
    List<Patient> findAllByOrderByNameAsc();
}
