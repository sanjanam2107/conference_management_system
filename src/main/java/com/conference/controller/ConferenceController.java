package com.conference.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ConferenceController {
    
    @GetMapping("/test")
    public String test() {
        return "Conference Management System is running!";
    }
} 