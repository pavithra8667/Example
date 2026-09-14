package com.infinite.ehrSystem.labResults.controller;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.infinite.ehrSystem.labResults.dto.LabOrderDTO;
import com.infinite.ehrSystem.labResults.service.LabOrderService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/lab-orders")
@RequiredArgsConstructor
public class LabOrderPageController {

    private final LabOrderService labOrderService;

    @GetMapping("/new")
    public String newOrder(
            @RequestParam UUID visitId,
            Model model) {

        model.addAttribute("visitId", visitId);

        return "labResult/lab-order-form";
    }

    @PostMapping("/save")
    public String saveOrder(
            @RequestParam UUID visitId,
            @RequestParam String testName) {

        LabOrderDTO dto = new LabOrderDTO();

        dto.setVisitId(visitId);
        dto.setTestName(testName);

        labOrderService.createLabOrder(dto);

        return "redirect:/lab-orders?visitId=" + visitId;
    }

    @GetMapping
    public String listOrders(
            @RequestParam UUID visitId,
            Model model) {

        model.addAttribute(
                "orders",
                labOrderService.getLabOrdersByVisitId(visitId)
        );

        model.addAttribute("visitId", visitId);

        return "labResult/lab-order-list";
    }

    @PostMapping("/delete/{id}")
    public String deleteOrder(
            @PathVariable UUID id,
            @RequestParam UUID visitId) {

        labOrderService.deleteLabOrder(id);

        return "redirect:/lab-orders?visitId=" + visitId;
    }
}
