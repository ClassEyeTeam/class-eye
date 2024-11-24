package com.classeye.studentservice.service.impl;

/**
 * @author moham
 **/


import com.classeye.studentservice.entity.Session;
import com.classeye.studentservice.repository.SessionRepository;
import com.classeye.studentservice.service.SessionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;

    @Override
    public Session saveSession(Session session) {
        log.info("Saving session with option ID: {}", session.getModuleOptionId());
        Session savedSession = sessionRepository.save(session);
        log.info("Session saved with ID: {}", savedSession.getId());
        return savedSession;
    }

    @Override
    public Optional<Session> getSessionById(Long id) {
        log.info("Fetching session with ID: {}", id);
        Optional<Session> session = sessionRepository.findById(id);
        if (session.isEmpty()) {
            log.warn("Session with ID {} not found", id);
        }
        return session;
    }

    @Override
    public List<Session> getAllSessions() {
        log.info("Fetching all sessions");
        return sessionRepository.findAll();
    }

    @Override
    public void deleteSession(Long id) {
        log.info("Deleting session with ID: {}", id);
        Session session = sessionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Session not found with ID: " + id));
        sessionRepository.delete(session);
        log.info("Session deleted with ID: {}", id);
    }

    @Override
    public List<Session> findByStudentId(Long studentId) {
        log.info("Fetching sessions for student ID: {}", studentId);
        return sessionRepository.findByAttendances_Student_Id(studentId);
    }
}

