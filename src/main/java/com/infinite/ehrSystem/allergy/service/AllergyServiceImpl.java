package com.infinite.ehrSystem.allergy.service;
import com.infinite.ehrSystem.allergy.dto.AllergyDTO;
import com.infinite.ehrSystem.allergy.entity.Allergy;
import com.infinite.ehrSystem.allergy.exception.AllergyNotFoundException;
import com.infinite.ehrSystem.allergy.exception.DuplicateAllergyException;
import com.infinite.ehrSystem.allergy.exception.InvalidAllergyException;
import com.infinite.ehrSystem.allergy.repository.AllergyRepository;
import com.infinite.ehrSystem.patient.entity.Patient;
import com.infinite.ehrSystem.patient.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
public class AllergyServiceImpl implements AllergyService {

    private final AllergyRepository allergyRepository;

    private final PatientRepository patientRepository;

    private static final Set<String> ALLOWED_SEVERITIES =
            Set.of(
                    "MILD",
                    "MODERATE",
                    "SEVERE"
            );

    public AllergyServiceImpl(
            AllergyRepository allergyRepository,
            PatientRepository patientRepository) {

        this.allergyRepository = allergyRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AllergyDTO> getAllergies(
            UUID patientId) {

        if (!patientRepository.existsById(patientId)) {

            throw new AllergyNotFoundException(
                    "Patient not found"
            );
        }

        return allergyRepository
                .findByPatientId(patientId)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public AllergyDTO addAllergy(
            UUID patientId,
            AllergyDTO allergyDTO) {

        Patient patient =
                patientRepository.findById(patientId)
                        .orElseThrow(() ->
                                new AllergyNotFoundException(
                                        "Patient not found"
                                ));

        if (allergyDTO.getAllergen() == null ||
                allergyDTO.getAllergen().isBlank()) {

            throw new InvalidAllergyException(
                    "Allergen is required"
            );
        }

        if (allergyDTO.getReaction() == null ||
                allergyDTO.getReaction().isBlank()) {

            throw new InvalidAllergyException(
                    "Reaction is required"
            );
        }

        if (!ALLOWED_SEVERITIES.contains(
                allergyDTO.getSeverity())) {

            throw new InvalidAllergyException(
                    "Severity must be MILD, MODERATE or SEVERE"
            );
        }

        if (allergyRepository
                .existsByPatientIdAndAllergen(
                        patientId,
                        allergyDTO.getAllergen()
                )) {

            throw new DuplicateAllergyException(
                    "This allergen already exists for the patient"
            );
        }

        Allergy allergy = new Allergy();

        allergy.setPatient(patient);
        allergy.setAllergen(allergyDTO.getAllergen());
        allergy.setReaction(allergyDTO.getReaction());
        allergy.setSeverity(allergyDTO.getSeverity());

        Allergy savedAllergy =
                allergyRepository.save(allergy);

        return convertToDTO(savedAllergy);
    }

    private AllergyDTO convertToDTO(
            Allergy allergy) {

        AllergyDTO dto = new AllergyDTO();

        dto.setId(allergy.getId());
        dto.setPatientId(
                allergy.getPatient().getId()
        );
        dto.setAllergen(
                allergy.getAllergen()
        );
        dto.setReaction(
                allergy.getReaction()
        );
        dto.setSeverity(
                allergy.getSeverity()
        );

        return dto;
    }
}