package com.conference.model;

import lombok.Data;

public interface Session {
    String getSessionId();
    String getTitle();
    String getDescription();
    int getCapacity();
    String getType();
} 