package com.infinite.ehrSystem.prescriptions.service;

import com.infinite.ehrSystem.exception.EmptyPrescriptionException;
import com.infinite.ehrSystem.exception.PrescriptionNotFoundException;
import com.infinite.ehrSystem.exception.VisitNotFoundException;
import com.infinite.ehrSystem.prescriptions.dto.PrescriptionDTO;
import com.infinite.ehrSystem.prescriptions.dto.PrescriptionItemDTO;
import com.infinite.ehrSystem.prescriptions.entity.Prescription;
import com.infinite.ehrSystem.prescriptions.entity.PrescriptionItem;
import com.infinite.ehrSystem.prescriptions.repository.PrescriptionRepository;
import com.infinite.ehrSystem.visit.entity.Visit;
import com.infinite.ehrSystem.visit.repository.VisitRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class PrescriptionServiceImpl
        implements PrescriptionService {

    private final PrescriptionRepository prescriptionRepository;
    private final VisitRepository visitRepository;

    public PrescriptionServiceImpl(
            PrescriptionRepository prescriptionRepository,
            VisitRepository visitRepository) {

        this.prescriptionRepository = prescriptionRepository;
        this.visitRepository = visitRepository;
    }

    @Override
    public PrescriptionDTO createPrescription(PrescriptionDTO dto) {
        if (dto.getItems() == null || dto.getItems().isEmpty()) {
            throw new EmptyPrescriptionException("Prescription must contain at least one medicine item");
        }

        Visit visit = visitRepository.findById(dto.getVisitId()).orElseThrow(() ->
                        new VisitNotFoundException("Visit not found with id: " + dto.getVisitId()));

        Prescription prescription = Prescription.builder()
                .visit(visit)
                .build();


        List<PrescriptionItem> items = new ArrayList<>();

        if (dto.getItems() != null) {

            for (PrescriptionItemDTO itemDTO : dto.getItems()) {
                PrescriptionItem item = PrescriptionItem.builder()
                                .medicineName(itemDTO.getMedicineName())
                                .dosage(itemDTO.getDosage())
                                .frequency(itemDTO.getFrequency())
                                .durationDays(itemDTO.getDurationDays())
                                .prescription(prescription)
                                .build();

                items.add(item);
            }
        }

        prescription.setItems(items);

        Prescription saved = prescriptionRepository.save(prescription);
        return convertToDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public PrescriptionDTO getPrescriptionById(UUID prescriptionId) {
        Prescription prescription = prescriptionRepository.findById(prescriptionId).orElseThrow(() ->
                                new PrescriptionNotFoundException("Prescription not found with id: " + prescriptionId));
        return convertToDTO(prescription);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PrescriptionDTO> getPrescriptionsByVisitId(UUID visitId) {
        if (!visitRepository.existsById(visitId)) {
            throw new VisitNotFoundException("Visit not found with id: " + visitId);
        }
        return prescriptionRepository
                .findByVisitId(visitId)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }
    @Override
    public PrescriptionDTO updatePrescription(UUID prescriptionId, PrescriptionDTO dto) {
        if (dto.getItems() == null || dto.getItems().isEmpty()) {
            throw new EmptyPrescriptionException("Prescription must contain at least one medicine item");
        }

        Prescription prescription = prescriptionRepository.findById(prescriptionId).orElseThrow(() ->
                                new PrescriptionNotFoundException("Prescription not found with id: " + prescriptionId));

        Visit visit =
                visitRepository.findById(dto.getVisitId()).orElseThrow(() ->
                                new VisitNotFoundException("Visit not found with id: " + dto.getVisitId()));
        prescription.setVisit(visit);

        List<PrescriptionItem> items =new ArrayList<>();
        if (dto.getItems() != null) {
            for (PrescriptionItemDTO itemDTO : dto.getItems()) {
                PrescriptionItem item = PrescriptionItem.builder()
                                .medicineName(itemDTO.getMedicineName())
                                .dosage(itemDTO.getDosage())
                                .frequency(itemDTO.getFrequency())
                                .durationDays(itemDTO.getDurationDays())
                                .prescription(prescription)
                                .build();

                items.add(item);
            }
        }

        prescription.setItems(items);
        Prescription updated = prescriptionRepository.save(prescription);
        return convertToDTO(updated);
    }

    @Override
    public void deletePrescription(UUID prescriptionId) {
        if (!prescriptionRepository.existsById(prescriptionId)) {
            throw new PrescriptionNotFoundException("Prescription not found with id: " + prescriptionId);
        }
        prescriptionRepository.deleteById(prescriptionId);
    }

    private PrescriptionDTO convertToDTO(Prescription prescription) {
        List<PrescriptionItemDTO> itemDTOs = new ArrayList<>();
        if (prescription.getItems() != null) {
            for (PrescriptionItem item : prescription.getItems()) {
                PrescriptionItemDTO itemDTO = PrescriptionItemDTO.builder()
                                .itemId(item.getItemId())
                                .medicineName(item.getMedicineName())
                                .dosage(item.getDosage())
                                .frequency(item.getFrequency())
                                .durationDays(item.getDurationDays())
                                .build();
                itemDTOs.add(itemDTO);
            }
        }

        return PrescriptionDTO.builder()
                .prescriptionId(prescription.getPrescriptionId())
                .visitId(prescription.getVisit().getId())
                .items(itemDTOs)
                .build();
    }
}