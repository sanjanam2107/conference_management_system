package com.conference.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reviewer")
public class ReviewerController {
    
    @GetMapping("/papers/assigned")
    public String viewAssignedPapers() {
        return "reviewer/assigned-papers";
    }
    
    @GetMapping("/papers/review")
    public String reviewPaper() {
        return "reviewer/review-paper";
    }
    
    @GetMapping("/reviews/submit")
    public String submitReview() {
        return "reviewer/submit-review";
    }
}