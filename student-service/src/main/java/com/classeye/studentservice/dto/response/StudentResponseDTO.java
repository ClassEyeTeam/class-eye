package com.classeye.studentservice.dto.response;

import com.classeye.studentservice.entity.AttendanceStatus;

import java.util.List;

/**
 * @author moham
 **/
public record StudentResponseDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        Long optionId,

        Boolean faceDetectionEnabled,
        AttendanceResponseDTO attendanceResponseDTO
) {
}

