package com.conference.service;

import com.conference.model.User;
import com.conference.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    private static final Logger logger = LoggerFactory.getLogger(CustomUserDetailsService.class);
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.debug("Attempting to load user by username: {}", username);
        
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> {
                logger.error("User not found with username: {}", username);
                return new UsernameNotFoundException("User not found with username: " + username);
            });

        logger.debug("Found user: {}, roles: {}", user.getUsername(), user.getRoles());
        
        var authorities = user.getRoles().stream()
            .map(role -> {
                String authorityRole = role.startsWith("ROLE_") ? role : "ROLE_" + role;
                logger.debug("Mapping role {} to authority {}", role, authorityRole);
                return new SimpleGrantedAuthority(authorityRole);
            })
            .collect(Collectors.toList());

        return org.springframework.security.core.userdetails.User
            .withUsername(user.getUsername())
            .password(user.getPassword())
            .authorities(authorities)
            .build();
    }
} 