package com.conference.controller;

import com.conference.model.Session;
import com.conference.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sessions")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @GetMapping
    public String listSessions(Model model) {
        model.addAttribute("sessions", sessionService.getAllSessions());
        return "sessions/list";
    }
} 