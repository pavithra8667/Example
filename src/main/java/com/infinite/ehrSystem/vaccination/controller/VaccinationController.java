package com.infinite.ehrSystem.vaccination.controller;

import com.infinite.ehrSystem.vaccination.dto.VaccinationDTO;
import com.infinite.ehrSystem.vaccination.service.VaccinationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vaccinations")
public class VaccinationController {

    @Autowired
    private VaccinationService vaccinationService;

    @PostMapping
    public VaccinationDTO saveVaccination(@RequestBody VaccinationDTO dto) {

        return vaccinationService.saveVaccination(dto);
    }

    @GetMapping
    public List<VaccinationDTO> vaccinationList(@RequestParam UUID patientId) {

        return vaccinationService.getVaccinationsByPatientId(patientId);
    }

    @GetMapping("/{vaccinationId}")
    public VaccinationDTO getVaccinationById(@PathVariable UUID vaccinationId) {

        return vaccinationService.getVaccinationById(vaccinationId);
    }

    @PutMapping("/{vaccinationId}")
    public VaccinationDTO updateVaccination(@PathVariable UUID vaccinationId,@RequestBody VaccinationDTO dto) {

        return vaccinationService.updateVaccination(vaccinationId, dto);
    }

    @DeleteMapping("/{vaccinationId}")
    public String deleteVaccination(@PathVariable UUID vaccinationId) {

        vaccinationService.deleteVaccination(vaccinationId);

        return "Vaccination deleted successfully";
    }
}