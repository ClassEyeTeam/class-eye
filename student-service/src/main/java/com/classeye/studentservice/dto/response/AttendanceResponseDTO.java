package com.classeye.studentservice.dto.response;

import com.classeye.studentservice.entity.AttendanceStatus;
import jakarta.persistence.Column;

import java.time.LocalDateTime;

/**
 * @author moham
 **/
public record AttendanceResponseDTO(
        Long id,
        StudentDTO student,
        Long sessionId,
        LocalDateTime startTime,
        AttendanceStatus status
) {}

