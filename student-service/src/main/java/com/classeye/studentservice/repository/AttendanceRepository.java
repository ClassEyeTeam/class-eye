package com.classeye.studentservice.repository;

/**
 * @author moham
 **/

import com.classeye.studentservice.entity.Attendance;

import com.classeye.studentservice.entity.AttendanceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

    // Find attendance records for a specific student
    List<Attendance> findByStudent_Id(Long studentId);

    // Find attendance records for a specific session
    List<Attendance> findBySession_Id(Long sessionId);

    // Find attendance records by status (e.g., PRESENT, ABSENT, LATE)
    List<Attendance> findByStatusAndStudentId(AttendanceStatus status, Long id);

    // Find attendance records for a specific student and session
    List<Attendance> findByStudent_IdAndSession_Id(Long studentId, Long sessionId);
}

