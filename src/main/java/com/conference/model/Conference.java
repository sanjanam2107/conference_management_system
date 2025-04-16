package com.conference.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
public class Conference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String description;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate = new Date();
}