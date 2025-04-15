package com.conference.observer;

public interface ConferenceEventListener {
    void onSessionUpdate(String sessionId, String updateType);
    void onRegistrationUpdate(String registrationId, String updateType);
} 