package com.classeye.studentservice.controller;

/**
 * @author moham
 **/

import com.classeye.studentservice.dto.request.AttendanceRequestDTO;
import com.classeye.studentservice.dto.response.AttendanceResponseDTO;
import com.classeye.studentservice.entity.AttendanceStatus;
import com.classeye.studentservice.service.AttendanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/attendances")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping
    public ResponseEntity<AttendanceResponseDTO> createAttendance(@Valid @RequestBody AttendanceRequestDTO attendanceRequestDTO) {
        AttendanceResponseDTO createdAttendance = attendanceService.saveAttendance(attendanceRequestDTO);
        return new ResponseEntity<>(createdAttendance, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttendanceResponseDTO> getAttendanceById(@PathVariable Long id) {
        AttendanceResponseDTO createdAttendance = attendanceService.getAttendanceById(id);
        return new ResponseEntity<>(createdAttendance, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AttendanceResponseDTO>> getAllAttendances() {
        List<AttendanceResponseDTO> attendances = attendanceService.getAllAttendances();
        return ResponseEntity.ok(attendances);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttendance(@PathVariable Long id) {
        attendanceService.deleteAttendance(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<AttendanceResponseDTO>> getAttendancesByStudentId(@PathVariable Long studentId) {
        List<AttendanceResponseDTO> attendances = attendanceService.findByStudentId(studentId);
        return ResponseEntity.ok(attendances);
    }

    @GetMapping("/session/{sessionId}")
    public ResponseEntity<List<AttendanceResponseDTO>> getAttendancesBySessionId(@PathVariable Long sessionId) {
        List<AttendanceResponseDTO> attendances = attendanceService.findBySessionId(sessionId);
        return ResponseEntity.ok(attendances);
    }

    @GetMapping("/status/{status}/student/{studentId}")
    public ResponseEntity<List<AttendanceResponseDTO>> getAttendancesByStatusAndStudentId(@PathVariable String status, @PathVariable Long studentId) {
        List<AttendanceResponseDTO> attendances = attendanceService.findByStatusAndStudentId(AttendanceStatus.valueOf(status), studentId);
        return ResponseEntity.ok(attendances);
    }

    @GetMapping("/student/{studentId}/session/{sessionId}")
    public ResponseEntity<AttendanceResponseDTO> getAttendancesByStudentIdAndSessionId(@PathVariable Long studentId, @PathVariable Long sessionId) {
        AttendanceResponseDTO attendances = attendanceService.findByStudentIdAndSessionId(studentId, sessionId);
        return ResponseEntity.ok(attendances);
    }
}
