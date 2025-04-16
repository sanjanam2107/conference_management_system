package com.conference.service.impl;

import com.conference.model.Conference;
import com.conference.repository.ConferenceRepository;
import com.conference.service.ConferenceService;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConferenceServiceImpl implements ConferenceService {
    
    private final ConferenceRepository conferenceRepository;

    @Override
    public List<Conference> getAllConferences() {
        return conferenceRepository.findAll();
    }

    @Override
    public Conference getConferenceById(Long id) {
        return conferenceRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Conference not found"));
    }

    @Override
    public Conference createConference(Conference conference) {
        return conferenceRepository.save(conference);
    }

    @Override
    public Conference updateConference(Conference conference) {
        return conferenceRepository.save(conference);
    }

    @Override
    public void deleteConference(Long id) {
        conferenceRepository.deleteById(id);
    }
}