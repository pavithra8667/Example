package com.infinite.ehrSystem.diagnosis.controller;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.infinite.ehrSystem.diagnosis.dto.DiagnosisDTO;
import com.infinite.ehrSystem.diagnosis.entity.DiagnosisType;
import com.infinite.ehrSystem.diagnosis.service.DiagnosisService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/diagnoses")
@RequiredArgsConstructor
public class DiagnosisPageController {

    private final DiagnosisService diagnosisService;

    @GetMapping("/new")
    public String newDiagnosis(
            @RequestParam UUID visitId,
            Model model) {

        model.addAttribute("visitId", visitId);
        model.addAttribute("types", DiagnosisType.values());

        return "diagnosis/diagnosis-form";
    }

    @PostMapping("/save")
    public String saveDiagnosis(
            @RequestParam UUID visitId,
            @RequestParam String diagnosisCode,
            @RequestParam String description,
            @RequestParam DiagnosisType diagnosisType) {

        DiagnosisDTO dto = new DiagnosisDTO();

        dto.setVisitId(visitId);
        dto.setDiagnosisCode(diagnosisCode);
        dto.setDescription(description);
        dto.setDiagnosisType(diagnosisType);

        diagnosisService.createDiagnosis(visitId, dto);

        return "redirect:/diagnoses?visitId=" + visitId;
    }

    @GetMapping
    public String listDiagnosis(
            @RequestParam UUID visitId,
            Model model) {

        model.addAttribute(
                "diagnoses",
                diagnosisService.getDiagnosesByVisit(visitId)
        );

        model.addAttribute("visitId", visitId);

        return "diagnosis/diagnosis-list";
    }

    @PostMapping("/delete/{id}")
    public String deleteDiagnosis(
            @PathVariable UUID id,
            @RequestParam UUID visitId) {

        diagnosisService.deleteDiagnosis(id);

        return "redirect:/diagnoses?visitId=" + visitId;
    }
}