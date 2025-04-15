package com.conference.model;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class PanelSession implements Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String sessionId;
    private String title;
    private String description;
    private int capacity;
    private String type = "PANEL";
    
    @ElementCollection
    private List<String> panelists;
    private String moderator;
} 