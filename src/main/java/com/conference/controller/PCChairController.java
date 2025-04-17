package com.conference.controller;

import com.conference.model.Conference;
import com.conference.model.Paper;
import com.conference.model.enums.PaperState;
import com.conference.service.ConferenceService;
import com.conference.service.PaperService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import lombok.RequiredArgsConstructor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;  // Add this import
import org.springframework.ui.Model;

@Controller
@RequestMapping("/pc-chair")
@RequiredArgsConstructor
public class PCChairController {
    private final ConferenceService conferenceService;
    private final PaperService paperService;

    @GetMapping("/conferences/create")
    public String createConference() {
        return "pc-chair/create-conference";
    }

    @PostMapping("/conferences/create")
    public String handleCreateConference(@RequestParam String name,
                                       @RequestParam String description,
                                       @RequestParam String startDate,
                                       @RequestParam String endDate) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            
            Conference conference = new Conference();
            conference.setName(name);
            conference.setDescription(description);
            conference.setStartDate(dateFormat.parse(startDate));
            conference.setEndDate(dateFormat.parse(endDate));
            
            conferenceService.createConference(conference);
            return "redirect:/dashboard";
        } catch (Exception e) {
            return "redirect:/error?message=" + e.getMessage();
        }
    }

    @PostMapping("/papers/{id}/decision")
    public String makeDecision(@PathVariable Long id, 
                             @RequestParam String decision, 
                             RedirectAttributes redirectAttributes) {
        try {
            Paper paper = paperService.getPaperById(id);
            
            // Verify all reviews are complete
            if (paper.getReviews().size() < 4) {
                throw new RuntimeException("Cannot make decision until all reviews are complete");
            }
            
            // Set paper state based on decision
            if (decision.equals("ACCEPT")) {
                paper.setState(PaperState.ACCEPTED);
                // You might want to add additional logic here for accepted papers
            } else {
                paper.setState(PaperState.REJECTED);
            }
            
            paperService.savePaper(paper);
            redirectAttributes.addFlashAttribute("success", 
                "Paper has been " + (decision.equals("ACCEPT") ? "accepted" : "rejected"));
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        
        return "redirect:/pc-chair/decisions";  // Update this return URL too
    }

    @GetMapping("/decisions")  // Changed from "/papers/decisions"
    public String showPaperDecisions(Model model) {
        try {
            List<Paper> papers = paperService.getAllSubmittedPapers();
            model.addAttribute("papers", papers);
            return "pc-chair/make-decisions";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }
}