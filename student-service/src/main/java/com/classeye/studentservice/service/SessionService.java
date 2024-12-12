package com.classeye.studentservice.service;

/**
 * @author moham
 **/
import com.classeye.studentservice.dto.request.SessionRequestDTO;
import com.classeye.studentservice.dto.response.SessionResponseDTO;

import java.util.List;
import java.util.Optional;

public interface SessionService {

    // CRUD Operations
    SessionResponseDTO saveSession(SessionRequestDTO sessionRequestDTO);
    SessionResponseDTO updateSession(Long id,SessionRequestDTO sessionRequestDTO);
    Optional<SessionResponseDTO> getSessionById(Long id);
    List<SessionResponseDTO> getAllSessions();
    void deleteSession(Long id);

    // Custom Methods
    List<SessionResponseDTO> findByStudentId(Long studentId);
}

