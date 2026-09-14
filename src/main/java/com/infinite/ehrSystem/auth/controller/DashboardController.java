package com.infinite.ehrSystem.auth.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(
            Authentication authentication,
            Model model) {

        System.out.println("DASHBOARD HIT");

        if (authentication != null) {

            System.out.println(
                    "USERNAME = " + authentication.getName()
            );

            System.out.println(
                    "AUTHORITIES = " +
                            authentication.getAuthorities()
            );

            model.addAttribute(
                    "role",
                    authentication.getAuthorities()
                            .iterator()
                            .next()
                            .getAuthority()
            );
        }

        return "dashboard";
    }
}