package com.classeye.studentservice.dto.request;

import com.classeye.studentservice.entity.AttendanceStatus;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

/**
 * @author moham
 **/


public record AttendanceRequestDTO(
        @NotNull(message = "Student ID cannot be null")
        Long studentId,

        @NotNull(message = "Session ID cannot be null")
        Long sessionId,
        LocalDateTime startTime,
        LocalDateTime endTime,

        @NotNull(message = "Status cannot be null")
        AttendanceStatus status
) {}

