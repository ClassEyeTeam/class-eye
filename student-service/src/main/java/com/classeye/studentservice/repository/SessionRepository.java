package com.classeye.studentservice.repository;

/**
 * @author moham
 **/
import com.classeye.studentservice.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    // Find sessions by attendance status of a specific student
    List<Session> findByAttendances_Student_Id(Long studentId);
}
