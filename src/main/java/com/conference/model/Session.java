package com.conference.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;

@Entity
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String sessionId;
    
    private String title;
    private String description;
    private int capacity;
    
    public abstract String getType();
} 