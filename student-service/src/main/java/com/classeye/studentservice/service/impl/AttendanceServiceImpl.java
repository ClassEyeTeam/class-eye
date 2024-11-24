package com.classeye.studentservice.service.impl;

import com.classeye.studentservice.dto.request.AttendanceRequestDTO;
import com.classeye.studentservice.dto.response.AttendanceResponseDTO;
import com.classeye.studentservice.entity.Attendance;
import com.classeye.studentservice.entity.AttendanceStatus;
import com.classeye.studentservice.mapper.AttendanceMapper;
import com.classeye.studentservice.repository.AttendanceRepository;
import com.classeye.studentservice.service.AttendanceService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class AttendanceServiceImpl implements AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final AttendanceMapper attendanceMapper;

    @Override
    public AttendanceResponseDTO saveAttendance(AttendanceRequestDTO attendanceRequestDTO) {
        log.info("Saving attendance for student with ID: {}", attendanceRequestDTO.studentId());
        Attendance attendance = attendanceMapper.toEntity(attendanceRequestDTO);
        Attendance savedAttendance = attendanceRepository.save(attendance);
        log.info("Attendance saved for student ID: {}", savedAttendance.getStudent().getId());
        return attendanceMapper.toDto(savedAttendance);
    }

    @Override
    public AttendanceResponseDTO getAttendanceById(Long id) {
        log.info("Fetching attendance with ID: {}", id);
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Attendance not found with ID: " + id));
        return attendanceMapper.toDto(attendance);
    }

    @Override
    public List<AttendanceResponseDTO> getAllAttendances() {
        log.info("Fetching all attendances");
        return attendanceRepository.findAll().stream()
                .map(attendanceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAttendance(Long id) {
        log.info("Deleting attendance with ID: {}", id);
        Attendance attendance = attendanceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Attendance not found with ID: " + id));
        attendanceRepository.delete(attendance);
        log.info("Attendance deleted with ID: {}", id);
    }

    @Override
    public List<AttendanceResponseDTO> findByStudentId(Long studentId) {
        log.info("Fetching attendances for student ID: {}", studentId);
        return attendanceRepository.findByStudent_Id(studentId).stream()
                .map(attendanceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AttendanceResponseDTO> findBySessionId(Long sessionId) {
        log.info("Fetching attendances for session ID: {}", sessionId);
        return attendanceRepository.findBySession_Id(sessionId).stream()
                .map(attendanceMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<AttendanceResponseDTO> findByStatusAndStudentId(AttendanceStatus status, Long studentId) {
        log.info("Fetching attendances with status {} for student ID: {}", status, studentId);
        return attendanceRepository.findByStatusAndStudentId(status, studentId).stream()
                .map(attendanceMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AttendanceResponseDTO> findByStudentIdAndSessionId(Long studentId, Long sessionId) {
        log.info("Fetching attendances for student ID: {} and session ID: {}", studentId, sessionId);
        return attendanceRepository.findByStudent_IdAndSession_Id(studentId, sessionId).stream()
                .map(attendanceMapper::toDto)
                .collect(Collectors.toList());
    }
}

