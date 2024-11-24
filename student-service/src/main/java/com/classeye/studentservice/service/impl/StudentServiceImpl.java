package com.classeye.studentservice.service.impl;

/**
 * @author moham
 **/

package com.classeye.studentservice.service.impl;

import com.classeye.studentservice.entity.Student;
import com.classeye.studentservice.exception.ResourceNotFoundException;
import com.classeye.studentservice.repository.StudentRepository;
import com.classeye.studentservice.service.StudentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public Student saveStudent(Student student) {
        log.info("Saving student with email: {}", student.getEmail());
        Student savedStudent = studentRepository.save(student);
        log.info("Student saved with ID: {}", savedStudent.getId());
        return savedStudent;
    }

    @Override
    public Optional<Student> getStudentById(Long id) {
        log.info("Fetching student with ID: {}", id);
        Optional<Student> student = studentRepository.findById(id);
        if (student.isEmpty()) {
            log.warn("Student with ID {} not found", id);
        }
        return student;
    }

    @Override
    public List<Student> getAllStudents() {
        log.info("Fetching all students");
        return studentRepository.findAll();
    }

    @Override
    public void deleteStudent(Long id) {
        log.info("Deleting student with ID: {}", id);
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with ID: " + id));
        studentRepository.delete(student);
        log.info("Student deleted with ID: {}", id);
    }

    @Override
    public Student findByEmail(String email) {
        log.info("Fetching student with email: {}", email);
        return studentRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with email: " + email));
    }

    @Override
    public List<Student> findByLastName(String lastName) {
        log.info("Fetching students with last name: {}", lastName);
        return studentRepository.findByLastName(lastName);
    }

    @Override
    public List<Student> findBySessionId(Long sessionId) {
        log.info("Fetching students for session ID: {}", sessionId);
        return studentRepository.findByAttendances_Session_Id(sessionId);
    }
}

