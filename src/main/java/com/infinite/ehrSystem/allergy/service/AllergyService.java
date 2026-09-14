package com.infinite.ehrSystem.allergy.service;

import com.infinite.ehrSystem.allergy.dto.AllergyDTO;

import java.util.List;
import java.util.UUID;

public interface AllergyService {

    List<AllergyDTO> getAllergies(
            UUID patientId
    );

    AllergyDTO addAllergy(
            UUID patientId,
            AllergyDTO allergyDTO
    );
}
