package com.classeye.studentservice.service;

import com.classeye.studentservice.dto.response.dashboard.AttendanceStatisticsResponseDTO;
import com.classeye.studentservice.dto.response.dashboard.ModuleStatisticsResponseDTO;
import com.classeye.studentservice.dto.response.dashboard.OptionStatisticsResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author sejja
 **/
public interface  AttendanceStatisticsService {
    AttendanceStatisticsResponseDTO getStatistics(Long studentId, Long optionId, LocalDateTime startDate, LocalDateTime endDate);
    List<OptionStatisticsResponseDTO> getOptionStatistics();

    List<ModuleStatisticsResponseDTO> getModuleStatistics(Long moduleOptionId);

}
