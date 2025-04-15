package com.conference.controller;

import com.conference.model.Session;
import com.conference.service.SessionService;
import com.conference.factory.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sessions")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private SessionFactory sessionFactory;

    @GetMapping
    public String listSessions(Model model) {
        model.addAttribute("sessions", sessionService.getAllSessions());
        return "sessions/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("sessionTypes", new String[]{"WORKSHOP", "KEYNOTE", "PANEL"});
        return "sessions/form";
    }

    @PostMapping("/new")
    public String createSession(@RequestParam String type, 
                              @RequestParam String title,
                              @RequestParam String description,
                              @RequestParam int capacity) {
        Session session = sessionFactory.createSession(type);
        session.setTitle(title);
        session.setDescription(description);
        session.setCapacity(capacity);
        sessionService.createSession(session);
        return "redirect:/sessions";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Session session = sessionService.getSessionById(id);
        model.addAttribute("session", session);
        model.addAttribute("sessionTypes", new String[]{"WORKSHOP", "KEYNOTE", "PANEL"});
        return "sessions/form";
    }

    @PostMapping("/{id}/edit")
    public String updateSession(@PathVariable String id,
                              @RequestParam String title,
                              @RequestParam String description,
                              @RequestParam int capacity) {
        Session session = sessionService.getSessionById(id);
        session.setTitle(title);
        session.setDescription(description);
        session.setCapacity(capacity);
        sessionService.updateSession(session);
        return "redirect:/sessions";
    }

    @GetMapping("/{id}/delete")
    public String deleteSession(@PathVariable String id) {
        sessionService.deleteSession(id);
        return "redirect:/sessions";
    }
} 