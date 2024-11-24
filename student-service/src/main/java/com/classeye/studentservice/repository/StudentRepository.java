package com.classeye.studentservice.repository;

import com.classeye.studentservice.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author moham
 **/
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // Find a student by email
    Optional<Student> findByEmail(String email);

    // Find all students with a specific last name
    List<Student> findByLastName(String lastName);

    // Find students who attended a specific session
    List<Student> findByAttendances_Session_Id(Long sessionId);
}
