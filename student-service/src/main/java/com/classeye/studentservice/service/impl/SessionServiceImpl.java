package com.classeye.studentservice.service.impl;

/**
 * @author moham
 **/


import com.classeye.studentservice.dto.ModuleOptionResponseDTO;
import com.classeye.studentservice.dto.request.SessionRequestDTO;
import com.classeye.studentservice.dto.response.SessionResponseDTO;
import com.classeye.studentservice.entity.Session;
import com.classeye.studentservice.feign.ModuleOptionFeignClient;
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
    private final ModuleOptionFeignClient moduleOptionFeignClient ;

    @Override
    public SessionResponseDTO saveSession(SessionRequestDTO sessionRequestDTO) {
        log.info("Saving session with module option ID: {}", sessionRequestDTO.moduleOptionId());
        validateModuleOption(sessionRequestDTO.moduleOptionId());
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
        validateModuleOption(sessionRequestDTO.moduleOptionId());

        existingSession.setModuleOptionId(sessionRequestDTO.moduleOptionId());
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
    public Optional<SessionResponseDTO> getSessionDtoById(Long id) {
        log.info("Fetching session with ID: {}", id);
        return sessionRepository.findById(id)
                .map(sessionMapper::toDto);
    }
    @Override
    public Session  getSessionById(Long id) {
        log.info("Fetching session with ID: {}", id);
        return sessionRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Session not found with ID: " + id)
        );
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
    @Override
    public List<SessionResponseDTO> findByOptionId(Long optionId) {
        log.info("Fetching sessions for option ID: {}", optionId);
        List<ModuleOptionResponseDTO> options = moduleOptionFeignClient.getAllModulesInOption(optionId);
        return options.stream()
                .flatMap(option -> sessionRepository.findByModuleOptionId(option.id()).stream())
                .map(sessionMapper::toDto)
                .collect(Collectors.toList());
    }





    private void validateModuleOption(Long optionId) {
        try{
            moduleOptionFeignClient.getModuleOptionById(optionId);
        } catch (Exception e) {
            throw new EntityNotFoundException("Module option not found with ID: " + optionId);
        }
    }
}
