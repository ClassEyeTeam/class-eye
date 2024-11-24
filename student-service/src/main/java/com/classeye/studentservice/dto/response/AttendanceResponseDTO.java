package com.classeye.studentservice.dto.response;

import com.classeye.studentservice.entity.AttendanceStatus;

/**
 * @author moham
 **/
public record AttendanceResponseDTO(
        Long id,
        Long studentId,
        Long sessionId,
        AttendanceStatus status
) {}

