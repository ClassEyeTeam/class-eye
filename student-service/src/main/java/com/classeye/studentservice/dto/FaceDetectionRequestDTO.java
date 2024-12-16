package com.classeye.studentservice.dto;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

/**
 * @author sejja
 **/
public record FaceDetectionRequestDTO(
        Long studentId,
        double confidence,
        OffsetDateTime timestamp
) {
}
