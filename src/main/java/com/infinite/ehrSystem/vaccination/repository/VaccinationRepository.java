package com.infinite.ehrSystem.vaccination.repository;

import com.infinite.ehrSystem.vaccination.entity.Vaccination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface VaccinationRepository
        extends JpaRepository<Vaccination, UUID> {

    List<Vaccination> findByPatientId(UUID patientId);

    List<Vaccination> findByPatientIdAndVaccineNameOrderByDoseNumberDesc(UUID patientId,String vaccineName);
}