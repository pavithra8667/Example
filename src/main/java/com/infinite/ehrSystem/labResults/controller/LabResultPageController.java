package com.infinite.ehrSystem.labResults.controller;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.infinite.ehrSystem.labResults.dto.LabResultDTO;
import com.infinite.ehrSystem.labResults.service.LabResultService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/lab-results")
@RequiredArgsConstructor
public class LabResultPageController {
    private final LabResultService labResultService;

    @GetMapping("/new")
    public String newResult(@RequestParam UUID labOrderId, Model model) {
        model.addAttribute("labOrderId", labOrderId);
        model.addAttribute("today", LocalDate.now());
        return "labResult/lab-result-form";
    }

    @PostMapping("/save")
    public String saveResult(@RequestParam UUID labOrderId, @RequestParam String resultValue,
            @RequestParam(required = false) String unit, @RequestParam String referenceRange,
            @RequestParam LocalDate resultDate) {
        LabResultDTO dto = new LabResultDTO();
        dto.setLabOrderId(labOrderId); dto.setResultValue(resultValue); dto.setUnit(unit);
        dto.setReferenceRange(referenceRange); dto.setResultDate(resultDate);
        LabResultDTO saved = labResultService.createLabResult(dto);
        return "redirect:/lab-results/view/" + saved.getId();
    }

    @GetMapping("/view/{id}")
    public String viewResult(@PathVariable UUID id, Model model) {
        model.addAttribute("result", labResultService.getLabResultById(id));
        return "labResult/lab-result-view";
    }
}
