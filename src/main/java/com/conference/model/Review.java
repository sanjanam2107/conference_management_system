package com.conference.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Paper paper;

    @ManyToOne
    private User reviewer;

    @Column(length = 2000)
    private String comments;

    private int score;

    @Temporal(TemporalType.TIMESTAMP)
    private Date submissionDate;
}