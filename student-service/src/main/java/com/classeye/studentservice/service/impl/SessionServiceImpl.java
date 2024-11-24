package com.classeye.studentservice.service.impl;

/**
 * @author moham
 **/


import com.classeye.studentservice.dto.request.SessionRequestDTO;
import com.classeye.studentservice.dto.response.SessionResponseDTO;
import com.classeye.studentservice.entity.Session;
import com.classeye.studentservice.mapper.SessionMapper;
import com.classeye.studentservice.repository.SessionRepository;
import com.classeye.studentservice.service.SessionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;
    private final SessionMapper sessionMapper;

    @Override
    public SessionResponseDTO saveSession(SessionRequestDTO sessionRequestDTO) {
        log.info("Saving session with module option ID: {}", sessionRequestDTO.optionId());
//        if (sessionRepository.findByModuleOptionId(sessionRequestDTO.getModuleOptionId()).isPresent()) {
//            log.error("Session with module option ID {} already exists", sessionRequestDTO.getModuleOptionId());
//            throw new DuplicateResourceException("Session with module option ID " + sessionRequestDTO.getModuleOptionId() + " already exists!");
//        }
        Session session = sessionMapper.toEntity(sessionRequestDTO);
        Session savedSession = sessionRepository.save(session);
        log.info("Session saved with ID: {}", savedSession.getId());
        return sessionMapper.toDto(savedSession);
    }

    @Override
    public SessionResponseDTO updateSession(Long id, SessionRequestDTO sessionRequestDTO) {
        log.info("Updating session with ID: {}", id);
        Session existingSession = sessionRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Session with ID {} not found", id);
                    return new EntityNotFoundException("Session not found with ID: " + id);
                });
        existingSession.setModuleOptionId(sessionRequestDTO.optionId());
        existingSession.setStartDateTime(sessionRequestDTO.startDateTime());
        existingSession.setEndDateTime(sessionRequestDTO.endDateTime());
        // Update other fields as needed
        Session updatedSession = sessionRepository.save(existingSession);
        log.info("Session updated with ID: {}", updatedSession.getId());
        return sessionMapper.toDto(updatedSession);
    }

    @Override
    public void deleteSession(Long id) {
        log.info("Deleting session with ID: {}", id);
        if (!sessionRepository.existsById(id)) {
            log.error("Session with ID {} not found", id);
            throw new EntityNotFoundException("Session not found with ID: " + id);
        }
        sessionRepository.deleteById(id);
        log.info("Session deleted with ID: {}", id);
    }

    @Override
    public Optional<SessionResponseDTO> getSessionById(Long id) {
        log.info("Fetching session with ID: {}", id);
        return sessionRepository.findById(id)
                .map(sessionMapper::toDto);
    }

    @Override
    public List<SessionResponseDTO> getAllSessions() {
        log.info("Fetching all sessions");
        return sessionRepository.findAll().stream()
                .map(sessionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SessionResponseDTO> findByStudentId(Long studentId) {
        log.info("Fetching sessions for student ID: {}", studentId);
        return sessionRepository.findByAttendances_Student_Id(studentId).stream()
                .map(sessionMapper::toDto)
                .collect(Collectors.toList());
    }
}
