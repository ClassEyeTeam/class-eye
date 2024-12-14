package com.classeye.studentservice.dto;

import java.time.LocalDateTime;

/**
 * @author sejja
 **/
public record FaceDetectionRequestDTO(
        Long studentId,
        double confidence,
        LocalDateTime timestamp
) {
}
