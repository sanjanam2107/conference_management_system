package com.conference.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(exclude = {"acceptedPapers", "conference"})
public class Proceedings {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(nullable = false)
    private String title;
    
    @Temporal(TemporalType.DATE)
    private Date publicationDate;
    
    @OneToMany
    private Set<Paper> acceptedPapers = new HashSet<>();
    
    @OneToOne
    @JoinColumn(name = "conference_id")
    private Conference conference;
} 