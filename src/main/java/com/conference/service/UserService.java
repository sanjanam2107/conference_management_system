package com.conference.service;

import com.conference.dto.RegistrationDto;
import com.conference.model.User;
import com.conference.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User registerNewUser(RegistrationDto registrationDto) {
        // Check if username already exists
        if (userRepository.findByUsername(registrationDto.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        // Create new user
        User user = new User();
        user.setUsername(registrationDto.getUsername());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
        
        // Set default role (ensure ROLE_ prefix)
        Set<String> roles = new HashSet<>();
        roles.add("ROLE_USER"); // Spring Security requires ROLE_ prefix
        user.setRoles(roles);

        // Save and return the new user
        User savedUser = userRepository.save(user);
        System.out.println("User registered: " + savedUser.getUsername() + " with roles: " + savedUser.getRoles());
        return savedUser;
    }
} 