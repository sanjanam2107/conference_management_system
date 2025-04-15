package com.conference.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pc-chair")
public class PCChairController {
    
    @GetMapping("/conferences/create")
    public String createConference() {
        return "pc-chair/create-conference";
    }
    
    @GetMapping("/conferences/manage")
    public String manageConference() {
        return "pc-chair/manage-conference";
    }
    
    @GetMapping("/reviewers/assign")
    public String assignReviewers() {
        return "pc-chair/assign-reviewers";
    }
    
    @GetMapping("/reviews/monitor")
    public String monitorReviews() {
        return "pc-chair/monitor-reviews";
    }
    
    @GetMapping("/decisions")
    public String makeDecisions() {
        return "pc-chair/make-decisions";
    }
}