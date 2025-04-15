package com.conference.service;

import com.conference.dto.RegistrationDto;
import com.conference.model.User;

public interface UserService {
    User registerUser(RegistrationDto registrationDto, String role);
}