package com.classeye.studentservice.dto.response.dashboard;

import java.util.List;

/**
 * @author sejja
 **/
public record OptionStatisticsResponseDTO(
        Long optionId,
        long totalSessions,
        long totalAttendances,
        long presentCount
) {


}