package com.infinite.ehrSystem.allergy.repository;

import com.infinite.ehrSystem.allergy.entity.Allergy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AllergyRepository extends JpaRepository<Allergy, UUID> {

    List<Allergy> findByPatientId(UUID patientId);

    boolean existsByPatientIdAndAllergen(
            UUID patientId,
            String allergen
    );

}