package com.conference.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Panel extends Session {
    private String moderator;
    private String[] panelists;
    private int duration; // in minutes
    private String topic;
    private String format; // e.g., "Q&A", "Discussion", "Debate"
    
    @Override
    public String getType() {
        return "PANEL";
    }
} 