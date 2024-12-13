package com.classeye.studentservice.dto.response.dashboard;

/**
 * @author sejja
 **/
public record OptionStatisticsResponseDTO(
        Long optionId,
        long totalSessions,
        long totalAttendances,
        long presentCount
) {}