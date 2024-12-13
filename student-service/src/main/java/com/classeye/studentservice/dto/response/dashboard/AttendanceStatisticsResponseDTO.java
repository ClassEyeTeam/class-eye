package com.classeye.studentservice.dto.response.dashboard;

/**
 * @author sejja
 **/
public record AttendanceStatisticsResponseDTO(
        long totalSessions,
        long presentCount,
        long absentCount,
        long lateCount
) {}
