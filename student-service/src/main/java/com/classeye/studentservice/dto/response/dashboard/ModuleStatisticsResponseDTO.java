package com.classeye.studentservice.dto.response.dashboard;

/**
 * @author sejja
 **/
public record ModuleStatisticsResponseDTO(
        Long sessionId,
        long totalAttendances,
        long presentCount
) {}
