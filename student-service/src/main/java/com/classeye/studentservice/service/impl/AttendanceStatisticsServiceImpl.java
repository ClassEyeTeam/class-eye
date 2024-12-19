package com.classeye.studentservice.service.impl;

import com.classeye.studentservice.dto.response.dashboard.*;
import com.classeye.studentservice.entity.Attendance;
import com.classeye.studentservice.entity.AttendanceStatus;
import com.classeye.studentservice.entity.Session;
import com.classeye.studentservice.repository.AttendanceRepository;
import com.classeye.studentservice.repository.SessionRepository;
import com.classeye.studentservice.service.AttendanceStatisticsService;
import com.classeye.studentservice.service.specification.AttendanceSpecification;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author sejja
 **/
@Service
@AllArgsConstructor
@Slf4j
public class AttendanceStatisticsServiceImpl implements AttendanceStatisticsService {
    private final AttendanceRepository attendanceRepository;
    private final SessionRepository sessionRepository;


    @Override
    public AttendanceStatisticsResponseDTO getStatistics(Long studentId, Long optionId, LocalDateTime startDate, LocalDateTime endDate) {
        Specification<Attendance> spec = Specification.where(null);

        if (studentId != null) {
            spec = spec.and(AttendanceSpecification.hasStudentId(studentId));
        }
        if (optionId != null) {
            spec = spec.and(AttendanceSpecification.hasOptionId(optionId));
        }
        if (startDate != null && endDate != null) {
            spec = spec.and(AttendanceSpecification.isBetweenDates(startDate, endDate));
        }

        List<Attendance> attendances = attendanceRepository.findAll(spec);

        long totalSessions = attendances.size();
        long presentCount = attendances.stream().filter(a -> a.getStatus() == AttendanceStatus.PRESENT).count();
        long absentCount = attendances.stream().filter(a -> a.getStatus() == AttendanceStatus.ABSENT).count();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // Calculate present and absent per day by grouping by the date part of createdAt
        Map<String, Map<AttendanceStatus, Long>> attendancePerDayMap = attendances.stream()
                .filter(a -> a.getStartTime() != null) // Filter out null start times
                .collect(Collectors.groupingBy(
                        a -> a.getStartTime().format(formatter),
                        Collectors.groupingBy(Attendance::getStatus, Collectors.counting())
                ));


        List<PresentDayDto> presentPerDay = attendancePerDayMap.entrySet().stream()
                .map(entry -> {
                    Map<AttendanceStatus, Long> statusCountMap = entry.getValue();
                    long present = statusCountMap.getOrDefault(AttendanceStatus.PRESENT, 0L);
                    long absent = statusCountMap.getOrDefault(AttendanceStatus.ABSENT, 0L);
                    return new PresentDayDto(entry.getKey(), present, absent);
                })
                .sorted((a, b) -> a.date().compareTo(b.date()))
                .collect(Collectors.toList());

        return new AttendanceStatisticsResponseDTO(totalSessions, presentCount, absentCount, presentPerDay);
    }

    @Override
    public List<OptionStatisticsResponseDTO> getOptionStatistics() {
        return sessionRepository.findAll().stream()
                .collect(Collectors.groupingBy(Session::getModuleOptionId))
                .entrySet().stream()
                .map(entry -> {
                    Long optionId = entry.getKey();
                    List<Session> sessions = entry.getValue();
                    long totalSessions = sessions.size();
                    long totalAttendances = sessions.stream()
                            .flatMap(session -> session.getAttendances().stream())
                            .count();
                    long presentCount = sessions.stream()
                            .flatMap(session -> session.getAttendances().stream())
                            .filter(attendance -> attendance.getStatus() == AttendanceStatus.PRESENT)
                            .count();
                    return new OptionStatisticsResponseDTO(optionId, totalSessions, totalAttendances, presentCount);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<OptionModuleStatisticsResponseDTO> getOptionStatistics(Long optionId) {
        return sessionRepository.findByModuleOptionId(optionId).stream()
                .collect(Collectors.groupingBy(Session::getModuleOptionId))
                .entrySet().stream()
                .map(entry -> {
                    Long optionIdKey = entry.getKey();
                    List<Session> sessions = entry.getValue();
                    long totalSessions = sessions.size();
                    long totalAttendances = sessions.stream()
                            .flatMap(session -> session.getAttendances().stream())
                            .count();
                    long presentCount = sessions.stream()
                            .flatMap(session -> session.getAttendances().stream())
                            .filter(attendance -> attendance.getStatus() == AttendanceStatus.PRESENT)
                            .count();

                    // Collect module statistics for each option
                    List<ModuleStatisticsResponseDTO> moduleStatistics = sessions.stream()
                            .collect(Collectors.groupingBy(Session::getId))
                            .entrySet().stream()
                            .map(moduleEntry -> {
                                Long moduleId = moduleEntry.getKey();
                                List<Session> moduleSessions = moduleEntry.getValue();
                                long moduleTotalAttendances = moduleSessions.stream()
                                        .flatMap(session -> session.getAttendances().stream())
                                        .count();
                                long modulePresentCount = moduleSessions.stream()
                                        .flatMap(session -> session.getAttendances().stream())
                                        .filter(attendance -> attendance.getStatus() == AttendanceStatus.PRESENT)
                                        .count();
                                return new ModuleStatisticsResponseDTO(moduleId, moduleTotalAttendances, modulePresentCount);
                            })
                            .collect(Collectors.toList());

                    return new OptionModuleStatisticsResponseDTO(optionIdKey, totalSessions, totalAttendances, presentCount, moduleStatistics);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<ModuleStatisticsResponseDTO> getModuleStatistics(Long moduleOptionId) {
        return sessionRepository.findByModuleOptionId(moduleOptionId).stream()
                .map(session -> {
                    long totalAttendances = session.getAttendances().size();
                    long presentCount = session.getAttendances().stream()
                            .filter(attendance -> attendance.getStatus() == AttendanceStatus.PRESENT)
                            .count();
                    return new ModuleStatisticsResponseDTO(session.getId(), totalAttendances, presentCount);
                })
                .collect(Collectors.toList());
    }
}

