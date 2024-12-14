package com.classeye.studentservice.dto.response.dashboard;

import java.time.LocalDateTime;

/**
 * @author sejja
 **/
public record PresentDayDto(
        LocalDateTime dateTime,
        long presentCount,
        long absentCount
) {
}
