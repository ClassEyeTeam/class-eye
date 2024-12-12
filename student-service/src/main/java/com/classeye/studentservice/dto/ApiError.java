package com.classeye.studentservice.dto;

import java.time.LocalDateTime;

/**
 * @author moham
 **/
public record ApiError(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path
) {
}
