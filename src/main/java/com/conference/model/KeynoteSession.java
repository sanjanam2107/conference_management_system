package com.conference.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class KeynoteSession implements Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String sessionId;
    private String title;
    private String description;
    private int capacity;
    private String type = "KEYNOTE";
    private String speakerName;
    private String speakerBio;
} 