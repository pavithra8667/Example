package com.infinite.ehrSystem.prescriptions.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.infinite.ehrSystem.prescriptions.dto.PrescriptionDTO;
import com.infinite.ehrSystem.prescriptions.dto.PrescriptionItemDTO;
import com.infinite.ehrSystem.prescriptions.service.PrescriptionService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/prescriptions")
@RequiredArgsConstructor
public class PrescriptionPageController {

    private final PrescriptionService prescriptionService;

    @GetMapping("/new")
    public String newPrescription(
            @RequestParam UUID visitId,
            Model model) {

        model.addAttribute("visitId", visitId);

        return "prescription/prescription-form";
    }

    @PostMapping("/save")
    public String savePrescription(
            @RequestParam UUID visitId,
            @RequestParam("medicineName") List<String> medicineNames,
            @RequestParam("dosage") List<String> dosages,
            @RequestParam("frequency") List<String> frequencies,
            @RequestParam("durationDays") List<Integer> durations) {

        List<PrescriptionItemDTO> items =
                new ArrayList<>();

        for (int i = 0; i < medicineNames.size(); i++) {

            PrescriptionItemDTO item =
                    new PrescriptionItemDTO();

            item.setMedicineName(
                    medicineNames.get(i)
            );

            item.setDosage(
                    dosages.get(i)
            );

            item.setFrequency(
                    frequencies.get(i)
            );

            item.setDurationDays(
                    durations.get(i)
            );

            items.add(item);
        }

        PrescriptionDTO dto =
                new PrescriptionDTO();

        dto.setVisitId(visitId);
        dto.setItems(items);

        prescriptionService.createPrescription(dto);

        return "redirect:/prescriptions?visitId=" + visitId;
    }

    @GetMapping
    public String listPrescriptions(
            @RequestParam UUID visitId,
            Model model) {

        model.addAttribute(
                "prescriptions",
                prescriptionService
                        .getPrescriptionsByVisitId(visitId)
        );

        model.addAttribute("visitId", visitId);

        return "prescription/prescription-list";
    }

    @GetMapping("/view/{id}")
    public String viewPrescription(
            @PathVariable UUID id,
            Model model) {

        model.addAttribute(
                "prescription",
                prescriptionService
                        .getPrescriptionById(id)
        );

        return "prescription/prescription-view";
    }

    @PostMapping("/delete/{id}")
    public String deletePrescription(
            @PathVariable UUID id,
            @RequestParam UUID visitId) {

        prescriptionService.deletePrescription(id);

        return "redirect:/prescriptions?visitId=" + visitId;
    }
}