package com.infinite.ehrSystem.diagnosis.service;
import com.infinite.ehrSystem.diagnosis.dto.DiagnosisDTO;
import com.infinite.ehrSystem.diagnosis.entity.Diagnosis;
import com.infinite.ehrSystem.diagnosis.entity.DiagnosisType;
import com.infinite.ehrSystem.diagnosis.repository.DiagnosisRepository;
import com.infinite.ehrSystem.visit.entity.Visit;
import com.infinite.ehrSystem.visit.entity.VisitStatus;
import com.infinite.ehrSystem.visit.repository.VisitRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DiagnosisServiceImpl implements DiagnosisService {
    private final DiagnosisRepository diagnosisRepository;
    private final VisitRepository visitRepository;

    @Override
    @Transactional
    public DiagnosisDTO createDiagnosis(UUID visitId, DiagnosisDTO diagnosisDTO) {
        Visit visit = visitRepository.findById(visitId)
                .orElseThrow(() -> new IllegalStateException("Visit not found with id: " + visitId));

        if (visit.getStatus() != VisitStatus.OPEN) {
            throw new IllegalStateException("Diagnosis can only be created for an OPEN visit");
        }
        if (diagnosisDTO.getDiagnosisType() == DiagnosisType.PRIMARY) {
            boolean primaryExists = diagnosisRepository.existsByVisitIdAndDiagnosisType(visitId, DiagnosisType.PRIMARY);
            if (primaryExists) {
                throw new IllegalStateException("A PRIMARY diagnosis already exists for this visit");
            }
        }
        Diagnosis diagnosis = new Diagnosis();
        diagnosis.setVisitId(visitId);
        diagnosis.setDiagnosisCode(diagnosisDTO.getDiagnosisCode().trim());
        diagnosis.setDescription(diagnosisDTO.getDescription().trim());
        diagnosis.setDiagnosisType(diagnosisDTO.getDiagnosisType());
        Diagnosis savedDiagnosis = diagnosisRepository.save(diagnosis);
        return convertToDTO(savedDiagnosis);
    }

    @Override
    @Transactional(readOnly = true)
    public DiagnosisDTO getDiagnosisById(UUID id) {
        Diagnosis diagnosis = diagnosisRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Diagnosis not found with id: " + id));
        return convertToDTO(diagnosis);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DiagnosisDTO> getDiagnosesByVisit(UUID visitId) {
        if (!visitRepository.existsById(visitId)) {throw new EntityNotFoundException("Visit not found with id: " + visitId);
        }
        return diagnosisRepository
                .findByVisitId(visitId)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }
    @Override
    @Transactional
    public void deleteDiagnosis(UUID id) {
        Diagnosis diagnosis = diagnosisRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Diagnosis not found with id: " + id));
        diagnosisRepository.delete(diagnosis);
    }
    private DiagnosisDTO convertToDTO(Diagnosis diagnosis) {
        DiagnosisDTO dto = new DiagnosisDTO();
        dto.setId(diagnosis.getId());
        dto.setVisitId(diagnosis.getVisitId());
        dto.setDiagnosisCode(diagnosis.getDiagnosisCode());
        dto.setDescription(diagnosis.getDescription());
        dto.setDiagnosisType(diagnosis.getDiagnosisType());
        return dto;
    }
}