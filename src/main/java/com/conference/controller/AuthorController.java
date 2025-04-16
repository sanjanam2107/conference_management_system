package com.conference.controller;

import com.conference.model.Paper;
import com.conference.service.ConferenceService;
import com.conference.service.PaperService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/author")
@RequiredArgsConstructor
public class AuthorController {
    
    private final PaperService paperService;
    private final ConferenceService conferenceService;

    @GetMapping("/papers/create")
    public String createPaper(Model model) {
        model.addAttribute("conferences", conferenceService.getAllConferences());
        return "author/create-paper";
    }
    
    @PostMapping("/papers/create")
    public String submitPaper(@RequestParam("title") String title,
                            @RequestParam("description") String description,
                            @RequestParam("conferenceId") Long conferenceId,
                            @RequestParam("paperFile") MultipartFile file,
                            Authentication authentication) throws IOException {
        Paper paper = new Paper();
        paper.setTitle(title);
        paper.setDescription(description);
        paper.setFileName(file.getOriginalFilename());
        paper.setContentType(file.getContentType());
        paper.setData(file.getBytes());
        
        paperService.submitPaper(paper, authentication.getName(), conferenceId);
        return "redirect:/author/papers/status";
    }

    @GetMapping("/papers/status")
    public String viewPaperStatus(Model model, Authentication authentication) {
        List<Paper> papers = paperService.getPapersByAuthor(authentication.getName());
        model.addAttribute("papers", papers);
        return "author/paper-status";
    }
}