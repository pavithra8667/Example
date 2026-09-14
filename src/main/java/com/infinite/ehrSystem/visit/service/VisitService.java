package com.infinite.ehrSystem.visit.service;

import com.infinite.ehrSystem.visit.dto.VisitDTO;

import java.util.List;
import java.util.UUID;

public interface VisitService {

    VisitDTO createVisit(VisitDTO visitDTO);

    VisitDTO getVisitById(UUID id);

    List<VisitDTO> getVisitsByPatient(UUID patientId);

    List<VisitDTO> getVisitsByDoctor(UUID doctorId);

    List<VisitDTO> getAllVisits();

    VisitDTO closeVisit(UUID id);
}