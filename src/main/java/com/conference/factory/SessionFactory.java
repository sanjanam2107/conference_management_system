package com.conference.factory;

import com.conference.model.*;
import org.springframework.stereotype.Component;

@Component
public class SessionFactory {
    public Session createSession(String type) {
        return switch (type.toUpperCase()) {
            case "WORKSHOP" -> new WorkshopSession();
            case "KEYNOTE" -> new KeynoteSession();
            case "PANEL" -> new PanelSession();
            default -> throw new IllegalArgumentException("Unknown session type: " + type);
        };
    }
} 