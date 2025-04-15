package com.conference.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/author")
public class AuthorController {
    
    @GetMapping("/papers/create")
    public String createPaper() {
        return "author/create-paper";
    }
    
    @GetMapping("/papers/edit")
    public String editPaper() {
        return "author/edit-paper";
    }
    
    @GetMapping("/papers/submit")
    public String submitPaper() {
        return "author/submit-paper";
    }
    
    @GetMapping("/papers/status")
    public String viewPaperStatus() {
        return "author/paper-status";
    }
}