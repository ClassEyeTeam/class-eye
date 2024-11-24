package com.classeye.studentservice.service;

/**
 * @author moham
 **/
import com.classeye.studentservice.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {


    Student saveStudent(Student student);
    Optional<Student> getStudentById(Long id);
    List<Student> getAllStudents();
    void deleteStudent(Long id);


    Student findByEmail(String email);
    List<Student> findByLastName(String lastName);
    List<Student> findBySessionId(Long sessionId);
}

