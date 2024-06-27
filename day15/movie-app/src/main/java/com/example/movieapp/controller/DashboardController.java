package com.example.movieapp.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/admin/dashboard")
@AllArgsConstructor
public class DashboardController {
    @GetMapping
    public String getDashboardPage(Model model) {
        return "admin/dashboard/dashboard";
    }
}
