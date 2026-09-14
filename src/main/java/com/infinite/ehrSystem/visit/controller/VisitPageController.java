package com.infinite.ehrSystem.visit.controller;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.infinite.ehrSystem.visit.dto.VisitDTO;
import com.infinite.ehrSystem.visit.service.VisitService;
import com.infinite.ehrSystem.auth.entity.UserStatus;
import com.infinite.ehrSystem.auth.repository.UserRepository;
import com.infinite.ehrSystem.patient.repository.PatientRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/visits")
@RequiredArgsConstructor
public class VisitPageController {

    private final VisitService visitService;
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    @GetMapping
    public String listVisits(Model model) {

        model.addAttribute(
                "visits",
                visitService.getAllVisits()
        );

        return "visit/visit-list";
    }

    @GetMapping("/new")
    public String newVisit(Model model) {

        model.addAttribute("visit", new VisitDTO());
        model.addAttribute(
                "patients",
                patientRepository.findAllByOrderByNameAsc()
        );
        model.addAttribute(
                "doctors",
                userRepository
                        .findByRole_NameContainingIgnoreCaseAndStatusOrderByUsernameAsc(
                                "DOCTOR",
                                UserStatus.ACTIVE
                        )
        );

        return "visit/visit-form";
    }

    @PostMapping("/save")
    public String saveVisit(
            @RequestParam UUID patientId,
            @RequestParam UUID doctorId,
            @RequestParam(required = false) String visitDate) {

        VisitDTO dto = new VisitDTO();

        dto.setPatientId(patientId);
        dto.setDoctorId(doctorId);

        if (visitDate != null && !visitDate.isBlank()) {
            dto.setVisitDate(
                    LocalDateTime.parse(visitDate)
            );
        }

        visitService.createVisit(dto);

        return "redirect:/visits";
    }

    @GetMapping("/details/{id}")
    public String visitDetails(
            @PathVariable UUID id,
            Model model) {

        model.addAttribute(
                "visit",
                visitService.getVisitById(id)
        );

        return "visit/visit-detail";
    }

    @PostMapping("/close/{id}")
    public String closeVisit(
            @PathVariable UUID id) {

        visitService.closeVisit(id);

        return "redirect:/visits";
    }
}
