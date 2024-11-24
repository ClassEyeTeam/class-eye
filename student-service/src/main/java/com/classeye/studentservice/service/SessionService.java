package com.classeye.studentservice.service;

/**
 * @author moham
 **/
import com.classeye.studentservice.entity.Session;

import java.util.List;
import java.util.Optional;

public interface SessionService {

    // CRUD Operations
    Session saveSession(Session session);
    Optional<Session> getSessionById(Long id);
    List<Session> getAllSessions();
    void deleteSession(Long id);

    // Custom Methods
    List<Session> findByStudentId(Long studentId);
}

