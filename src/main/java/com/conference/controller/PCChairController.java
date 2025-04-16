package com.conference.controller;

import com.conference.model.Conference;
import com.conference.service.ConferenceService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/pc-chair")
@RequiredArgsConstructor
public class PCChairController {

    private final ConferenceService conferenceService;

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
}