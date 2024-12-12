package com.classeye.studentservice.dto.request;

import com.classeye.studentservice.entity.AttendanceStatus;
import jakarta.validation.constraints.NotNull;

/**
 * @author moham
 **/


public record AttendanceRequestDTO(
        @NotNull(message = "Student ID cannot be null")
        Long studentId,

        @NotNull(message = "Session ID cannot be null")
        Long sessionId,

        @NotNull(message = "Status cannot be null")
        AttendanceStatus status
) {}

