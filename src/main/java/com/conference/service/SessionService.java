package com.conference.service;

import com.conference.model.Session;
import java.util.List;

public interface SessionService {
    List<Session> getAllSessions();
    Session getSessionById(String id);
    Session createSession(Session session);
    Session updateSession(Session session);
    void deleteSession(String id);
} 