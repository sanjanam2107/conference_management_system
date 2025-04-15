package com.conference.service.impl;

import com.conference.model.Session;
import com.conference.repository.SessionRepository;
import com.conference.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionServiceImpl implements SessionService {

    @Autowired
    private SessionRepository sessionRepository;

    @Override
    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }

    @Override
    public Session getSessionById(String id) {
        return sessionRepository.findById(id).orElse(null);
    }

    @Override
    public Session createSession(Session session) {
        return sessionRepository.save(session);
    }

    @Override
    public Session updateSession(Session session) {
        return sessionRepository.save(session);
    }

    @Override
    public void deleteSession(String id) {
        sessionRepository.deleteById(id);
    }
} 