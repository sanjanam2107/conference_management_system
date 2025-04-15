package com.conference.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    private Set<String> roles = new HashSet<>();

    public static final String ROLE_CONFERENCE_USER = "ROLE_CONFERENCE_USER";
    public static final String ROLE_PC_CHAIR = "ROLE_PC_CHAIR";
    public static final String ROLE_AUTHOR = "ROLE_AUTHOR";
    public static final String ROLE_REVIEWER = "ROLE_REVIEWER";
}