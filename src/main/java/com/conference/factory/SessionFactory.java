package com.conference.factory;

import org.springframework.stereotype.Component;

import com.conference.model.Keynote;
import com.conference.model.Panel;
import com.conference.model.Session;
import com.conference.model.Talk;
import com.conference.model.Workshop;

@Component
public class SessionFactory {
    public Session createSession(String type) {
        return switch (type.toUpperCase()) {
            case "WORKSHOP" -> new Workshop();
            case "KEYNOTE" -> new Keynote();
            case "PANEL" -> new Panel();
            case "TALK" -> new Talk();
            default -> throw new IllegalArgumentException("Unknown session type: " + type);
        };
    }
}