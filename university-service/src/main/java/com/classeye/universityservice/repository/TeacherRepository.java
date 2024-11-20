package com.classeye.universityservice.repository;

import com.classeye.universityservice.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author sejja
 **/
@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    public Optional<Teacher> getTeacherByName(String name);
}
