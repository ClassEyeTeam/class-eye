package com.classeye.studentservice.controller;

import com.classeye.studentservice.dto.response.dashboard.AttendanceStatisticsResponseDTO;
import com.classeye.studentservice.dto.response.dashboard.ModuleStatisticsResponseDTO;
import com.classeye.studentservice.dto.response.dashboard.OptionStatisticsResponseDTO;
import com.classeye.studentservice.service.AttendanceStatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author sejja
 **/

@RestController
@RequiredArgsConstructor
public class AttendanceStatisticsController {

    private final AttendanceStatisticsService attendanceStatisticsService;

    @GetMapping("/statistics")
    public AttendanceStatisticsResponseDTO getStatistics(
            @RequestParam(required = false) Long studentId,
            @RequestParam(required = false) Long optionId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return attendanceStatisticsService.getStatistics(studentId, optionId, startDate, endDate);
    }

    @GetMapping("/statistics/options")
    public List<OptionStatisticsResponseDTO> getOptionStatistics() {
        return attendanceStatisticsService.getOptionStatistics();
    }

    @GetMapping("/statistics/modules")
    public List<ModuleStatisticsResponseDTO> getModuleStatistics(@RequestParam Long moduleOptionId) {
        return attendanceStatisticsService.getModuleStatistics(moduleOptionId);
    }
}