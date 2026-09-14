package com.infinite.ehrSystem.prescriptions.service;

import com.infinite.ehrSystem.prescriptions.dto.PrescriptionDTO;

import java.util.List;
import java.util.UUID;

public interface PrescriptionService {

    PrescriptionDTO createPrescription(
            PrescriptionDTO dto
    );

    PrescriptionDTO getPrescriptionById(
            UUID prescriptionId
    );

    List<PrescriptionDTO> getPrescriptionsByVisitId(
            UUID visitId
    );

    PrescriptionDTO updatePrescription(
            UUID prescriptionId,
            PrescriptionDTO dto
    );

    void deletePrescription(
            UUID prescriptionId
    );
}