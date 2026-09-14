package com.infinite.ehrSystem.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login() {

        System.out.println("LOGIN PAGE HIT");

        return "login";
    }
}