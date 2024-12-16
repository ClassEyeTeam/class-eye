package com.classeye.studentservice.dto.response.dashboard;

import java.util.List;

/**
 * @author sejja
 **/
public record AttendanceStatisticsResponseDTO(
        long totalSessions,
        long presentCount,
        long absentCount,
        List<PresentDayDto> presentDays
) {}
