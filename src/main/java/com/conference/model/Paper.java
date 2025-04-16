package com.conference.model;

import com.conference.model.enums.PaperState;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Paper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String description;
    
    @Lob
    private byte[] data;
    private String fileName;
    private String contentType;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Conference conference;
    
    @OneToMany(mappedBy = "paper", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Author> authors = new HashSet<>();
    
    @OneToMany(mappedBy = "paper", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reviewer> reviewers = new HashSet<>();
    
    @OneToMany(mappedBy = "paper", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Review> reviews = new HashSet<>();
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date submissionDate;
    
    private Integer version;
    
    @Enumerated(EnumType.STRING)
    private PaperState state;
}