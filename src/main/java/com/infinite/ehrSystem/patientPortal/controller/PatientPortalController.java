package com.infinite.ehrSystem.patientPortal.controller;

import com.infinite.ehrSystem.patientPortal.dto.PatientPortalDTO;
import com.infinite.ehrSystem.patientPortal.service.PatientPortalService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/patient-portal")
public class PatientPortalController {

    private final PatientPortalService patientPortalService;

    public PatientPortalController(
            PatientPortalService patientPortalService) {

        this.patientPortalService = patientPortalService;
    }

    @GetMapping
    public String dashboard(
            @AuthenticationPrincipal UserDetails userDetails,
            Model model) {

        PatientPortalDTO portal =
                patientPortalService.getPatientPortal(
                        userDetails.getUsername());

        model.addAttribute("portal", portal);

        return "portal-dashboard";
    }
}