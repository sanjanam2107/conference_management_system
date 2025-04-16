package com.conference.model;

import com.conference.model.enums.PaperState;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;  // Add this import
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = {"conference", "reviews", "authors", "reviewers"})
public class Paper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @Column(length = 2000)
    private String description;
    
    @Enumerated(EnumType.STRING)
    private PaperState state = PaperState.CREATED;
    
    @ManyToOne
    @JoinColumn(name = "conference_id")
    private Conference conference;
    
    @Lob
    private byte[] data;
    
    private String fileName;
    private String contentType;
    private int version;
    
    @OneToMany(mappedBy = "paper", cascade = CascadeType.ALL)
    private Set<Review> reviews = new HashSet<>();
    
    @OneToMany(mappedBy = "paper", cascade = CascadeType.ALL)
    private Set<Author> authors = new HashSet<>();
    
    @OneToMany(mappedBy = "paper", cascade = CascadeType.ALL)
    private Set<Reviewer> reviewers = new HashSet<>();
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date submissionDate;
}