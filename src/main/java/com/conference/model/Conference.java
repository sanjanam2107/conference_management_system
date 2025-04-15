package com.conference.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import com.conference.model.enums.ConferenceState;
import com.conference.observer.ConferenceStateObserver;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(exclude = {"conferenceUsers", "submittedPapers", "proceedings"})
public class Conference {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;
    
    @Column(length = 2000)
    private String description;
    
    @Enumerated(EnumType.STRING)
    private ConferenceState state = ConferenceState.CREATED;
    
    @OneToMany(mappedBy = "conference", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ConferenceUser> conferenceUsers = new HashSet<>();
    
    @OneToMany(mappedBy = "conference", cascade = CascadeType.ALL)
    private Set<Paper> submittedPapers = new HashSet<>();
    
    @OneToOne(mappedBy = "conference", cascade = CascadeType.ALL)
    private Proceedings proceedings;
    
    @Transient
    private Set<ConferenceStateObserver> observers = new HashSet<>();
    
    public void addObserver(ConferenceStateObserver observer) {
        observers.add(observer);
    }
    
    public void removeObserver(ConferenceStateObserver observer) {
        observers.remove(observer);
    }
    
    public void setState(ConferenceState newState) {
        ConferenceState oldState = this.state;
        this.state = newState;
        notifyObservers(oldState, newState);
    }
    
    private void notifyObservers(ConferenceState oldState, ConferenceState newState) {
        observers.forEach(observer -> observer.onStateChange(this, oldState, newState));
    }
} 