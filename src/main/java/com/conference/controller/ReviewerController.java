package com.conference.controller;

import com.conference.model.Paper;
import com.conference.service.PaperService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/reviewer")
@RequiredArgsConstructor
public class ReviewerController {
    
    private final PaperService paperService;

    @GetMapping("/papers")
    public String viewPapers(Model model) {
        List<Paper> papers = paperService.getAllSubmittedPapers();
        log.info("Found {} papers for review", papers.size());
        
        if (papers.isEmpty()) {
            log.warn("No papers found for review");
            model.addAttribute("error", "No papers are currently available for review");
        }
        
        model.addAttribute("papers", papers);
        return "reviewer/papers";
    }
    
    @GetMapping("/papers/{id}/review")
    public String reviewPaper(@PathVariable Long id, Model model) {
        Paper paper = paperService.getPaperById(id);
        model.addAttribute("paper", paper);
        return "reviewer/review-paper";
    }
    
    @PostMapping("/papers/{id}/submit-review")
    public String submitReview(@PathVariable Long id,
                             @RequestParam String comments,
                             @RequestParam int score,
                             Authentication authentication,
                             RedirectAttributes redirectAttributes) {
        try {
            paperService.submitReview(id, authentication.getName(), comments, score);
            redirectAttributes.addFlashAttribute("success", "Review submitted successfully!");
            return "redirect:/reviewer/papers";
        } catch (Exception e) {
            log.error("Error submitting review for paper {}: {}", id, e.getMessage());
            redirectAttributes.addFlashAttribute("error", "Error submitting review: " + e.getMessage());
            return "redirect:/reviewer/papers/" + id + "/review";
        }
    }
    
    @GetMapping("/papers/{id}/download")
    public ResponseEntity<byte[]> downloadPaper(@PathVariable Long id) {
        Paper paper = paperService.getPaperById(id);
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(paper.getContentType()));
        headers.setContentDispositionFormData("attachment", paper.getFileName());
        
        return new ResponseEntity<>(paper.getData(), headers, HttpStatus.OK);
    }

    @GetMapping("/reviews")
    public String viewMyReviews(Model model, Authentication authentication) {
        List<Paper> reviewedPapers = paperService.getPapersByReviewer(authentication.getName());
        model.addAttribute("papers", reviewedPapers);
        return "reviewer/my-reviews";
    }
}