package com.classeye.studentservice.repository;

/**
 * @author moham
 **/
import com.classeye.studentservice.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {
    // Find sessions by attendance status of a specific student
    List<Session> findByAttendances_Student_Id(Long studentId);

    List<Session> findByEndDateTimeBefore(LocalDateTime now);

    List<Session> findByModuleOptionId(Long moduleOptionId);

    @Query("SELECT s FROM Session s WHERE s.moduleOptionId = :moduleOptionId AND :timestamp BETWEEN s.startDateTime AND s.endDateTime")

    Optional<Session> findCurrentSessionForStudent(Long moduleOptionId, LocalDateTime timestamp);
}
