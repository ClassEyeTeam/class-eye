# class-eyes: 2



### statistic documantation
Here is the documentation for the Attendance Statistics Service with filtering capabilities:

## Attendance Statistics Service

### Overview

The Attendance Statistics Service provides endpoints to fetch attendance statistics for students, modules, and options. It supports filtering by student ID, option ID, and date range. The service is designed to be used in a reporting dashboard with various filtering options.

### Endpoints

#### 1. Get Attendance Statistics

**URL:** `/statistics`

**Method:** `GET`

**Description:** Fetches attendance statistics based on the provided filters.

**Parameters:**
- `studentId` (optional): Filter by student ID.
- `optionId` (optional): Filter by option ID.
- `startDate` (optional): Filter by start date (ISO date-time format).
- `endDate` (optional): Filter by end date (ISO date-time format).

**Response:**
```java
public record AttendanceStatisticsResponseDTO(
    long totalSessions,
    long presentCount,
    long absentCount,
    long lateCount
) {}
```

#### 2. Get Option Statistics

**URL:** `/statistics/options`

**Method:** `GET`

**Description:** Fetches attendance statistics for all options.

**Response:**
```java
public record OptionStatisticsResponseDTO(
    Long optionId,
    long totalSessions,
    long totalAttendances,
    long presentCount
) {}
```

#### 3. Get Module Statistics

**URL:** `/statistics/modules`

**Method:** `GET`

**Description:** Fetches attendance statistics for a specific module option.

**Parameters:**
- `moduleOptionId` (required): The ID of the module option.

**Response:**
```java
public record ModuleStatisticsResponseDTO(
    Long sessionId,
    long totalAttendances,
    long presentCount
) {}
```

### Implementation Details

#### AttendanceStatisticsService

This service handles the logic for generating attendance statistics.

```java
package com.classeye.studentservice.service.impl;

import com.classeye.studentservice.dto.response.dashboard.AttendanceStatisticsResponseDTO;
import com.classeye.studentservice.dto.response.dashboard.ModuleStatisticsResponseDTO;
import com.classeye.studentservice.dto.response.dashboard.OptionStatisticsResponseDTO;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
        long lateCount = attendances.stream().filter(a -> a.getStatus() == AttendanceStatus.RETARD).count();

        return new AttendanceStatisticsResponseDTO(totalSessions, presentCount, absentCount, lateCount);
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
```

#### AttendanceStatisticsController

This controller exposes the endpoints for fetching the statistics.

```java
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
```

#### AttendanceSpecification

This class provides filtering capabilities using JPA Specifications.

```java
package com.classeye.studentservice.service.specification;

import com.classeye.studentservice.entity.Attendance;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class AttendanceSpecification {

    public static Specification<Attendance> hasStudentId(Long studentId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("student").get("id"), studentId);
    }

    public static Specification<Attendance> hasOptionId(Long optionId) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("student").get("optionId"), optionId);
    }

    public static Specification<Attendance> isBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("session").get("startDateTime"), startDate, endDate);
    }
}
```

#### AttendanceRepository

Ensure that `AttendanceRepository` extends `JpaSpecificationExecutor` to support specifications.

```java
package com.classeye.studentservice.repository;

import com.classeye.studentservice.entity.Attendance;
import com.classeye.studentservice.entity.AttendanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long>, JpaSpecificationExecutor<Attendance> {

    List<Attendance> findByStudent_Id(Long studentId);

    List<Attendance> findBySession_Id(Long sessionId);

    List<Attendance> findByStatusAndStudentId(AttendanceStatus status, Long id);

    Attendance findByStudent_IdAndSession_Id(Long studentId, Long sessionId);
}
```

### Summary

- **AttendanceStatisticsService**: Handles the logic for generating attendance statistics.
- **AttendanceStatisticsController**: Exposes the endpoints to fetch statistics.
- **AttendanceSpecification**: Provides filtering capabilities using JPA Specifications.
- **AttendanceRepository**: Supports specifications for querying the database.

This setup allows for flexible and powerful filtering of attendance data for different roles and periods, providing a rich dashboard for administrators, teachers, and students.