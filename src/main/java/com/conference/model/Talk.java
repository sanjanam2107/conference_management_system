package com.conference.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Talk extends Session {
    private String speaker;
    private int duration; // in minutes
    private String level; // e.g., "Beginner", "Intermediate", "Advanced"
    
    @Override
    public String getType() {
        return "TALK";
    }
} 