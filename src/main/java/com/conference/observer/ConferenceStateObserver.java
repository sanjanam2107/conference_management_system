package com.conference.observer;

import com.conference.model.Conference;
import com.conference.model.enums.ConferenceState;

public interface ConferenceStateObserver {
    void onStateChange(Conference conference, ConferenceState oldState, ConferenceState newState);
}