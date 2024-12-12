package com.classeye.studentservice.controller;

/**
 * @author moham
 **/
import com.classeye.studentservice.dto.request.SessionRequestDTO;
import com.classeye.studentservice.dto.response.SessionResponseDTO;
import com.classeye.studentservice.service.SessionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sessions")
@RequiredArgsConstructor
public class SessionController {

    private final SessionService sessionService;

    @PostMapping
    public ResponseEntity<SessionResponseDTO> createSession(@Valid @RequestBody SessionRequestDTO sessionRequestDTO) {
        SessionResponseDTO createdSession = sessionService.saveSession(sessionRequestDTO);
        return new ResponseEntity<>(createdSession, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SessionResponseDTO> updateSession(@PathVariable Long id, @Valid @RequestBody SessionRequestDTO sessionRequestDTO) {
        SessionResponseDTO updatedSession = sessionService.updateSession(id, sessionRequestDTO);
        return ResponseEntity.ok(updatedSession);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSession(@PathVariable Long id) {
        sessionService.deleteSession(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessionResponseDTO> getSessionById(@PathVariable Long id) {
        Optional<SessionResponseDTO> session = sessionService.getSessionById(id);
        return session.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<SessionResponseDTO>> getAllSessions() {
        List<SessionResponseDTO> sessions = sessionService.getAllSessions();
        return ResponseEntity.ok(sessions);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<SessionResponseDTO>> getSessionsByStudentId(@PathVariable Long studentId) {
        List<SessionResponseDTO> sessions = sessionService.findByStudentId(studentId);
        return ResponseEntity.ok(sessions);
    }
}
