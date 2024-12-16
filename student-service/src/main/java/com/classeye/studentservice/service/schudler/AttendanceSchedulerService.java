package com.classeye.studentservice.service.schudler;

import com.classeye.studentservice.entity.Attendance;
import com.classeye.studentservice.entity.AttendanceStatus;
import com.classeye.studentservice.entity.Session;
import com.classeye.studentservice.repository.AttendanceRepository;
import com.classeye.studentservice.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author sejja
 **/
@Service
@RequiredArgsConstructor
@Slf4j
public class AttendanceSchedulerService {

    private final SessionRepository sessionRepository;
    private final AttendanceRepository attendanceRepository;

    @Scheduled(cron = "0 0 * * * ?") // Runs every hour
    public void markAbsentStudents() {
        log.info("Running scheduled task to mark absent students");
        List<Session> endedSessions = sessionRepository.findByEndDateTimeBefore(LocalDateTime.now());

        for (Session session : endedSessions) {
            List<Attendance> attendances = attendanceRepository.findBySession_Id(session.getId());
            for (Attendance attendance : attendances) {
                if (attendance.getStatus() == AttendanceStatus.NOT_RECORDED) {
                    attendance.setStatus(AttendanceStatus.ABSENT);
                    attendanceRepository.save(attendance);
                    log.info("Marked student ID {} as absent for session ID {}", attendance.getStudent().getId(), session.getId());
                }
            }
        }
    }
}