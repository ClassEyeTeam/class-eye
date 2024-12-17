package com.classeye.studentservice.dto.response.dashboard;

/**
 * @author sejja
 **/
public record PresentDayDto(
        String date,
        long present,
        long absent
) {
}
