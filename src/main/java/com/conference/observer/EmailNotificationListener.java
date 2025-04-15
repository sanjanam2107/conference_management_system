package com.conference.observer;

import org.springframework.stereotype.Component;

@Component
public class EmailNotificationListener implements ConferenceEventListener {
    @Override
    public void onSessionUpdate(String sessionId, String updateType) {
        // In a real application, this would send an email
        System.out.println("Sending email notification for session update: " + sessionId + " - " + updateType);
    }

    @Override
    public void onRegistrationUpdate(String registrationId, String updateType) {
        // In a real application, this would send an email
        System.out.println("Sending email notification for registration update: " + registrationId + " - " + updateType);
    }
} 