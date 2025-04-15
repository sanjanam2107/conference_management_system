package com.conference.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Keynote extends Session {
    private String keynoteSpeaker;
    private String speakerBio;
    private int duration; // in minutes
    private String topic;
    private boolean isOpeningKeynote;
    
    @Override
    public String getType() {
        return "KEYNOTE";
    }
} 