package com.nhomA.mockproject.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping
    public String getAdmin() {
        return "Welcome to Admin page";
    }

    @PostMapping
    public String postAdmin() {
        return "Welcome to Admin page using POST HTTP Method";
    }
}
