package com.conference.service;

import com.conference.model.Conference;
import java.util.List;

public interface ConferenceService {
    List<Conference> getAllConferences();
    Conference getConferenceById(Long id);
    Conference createConference(Conference conference);
    Conference updateConference(Conference conference);
    void deleteConference(Long id);
}