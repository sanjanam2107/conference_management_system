package com.conference.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Workshop extends Session {
    private String instructor;
    private int capacity;
    private int duration; // in minutes
    private String prerequisites;
    private String materials;
    
    @Override
    public String getType() {
        return "WORKSHOP";
    }
} 