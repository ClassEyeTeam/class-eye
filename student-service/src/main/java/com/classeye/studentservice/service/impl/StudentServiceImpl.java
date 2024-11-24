package com.classeye.studentservice.service.impl;

/**
 * @author moham
 **/


import com.classeye.studentservice.dto.request.StudentRequestDTO;
import com.classeye.studentservice.dto.response.AttendanceResponseDTO;
import com.classeye.studentservice.dto.response.StudentResponseDTO;
import com.classeye.studentservice.entity.Student;
import com.classeye.studentservice.exception.DuplicateResourceException;
import com.classeye.studentservice.mapper.StudentMapper;
import com.classeye.studentservice.repository.StudentRepository;
import com.classeye.studentservice.service.AttendanceService;
import com.classeye.studentservice.service.StudentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final AttendanceService attendanceService;
    private final StudentMapper studentMapper;

    @Override
    public StudentResponseDTO saveStudent(StudentRequestDTO studentRequestDTO) {
        log.info("Saving student with email: {}", studentRequestDTO.email());
        if (studentRepository.findByEmail(studentRequestDTO.email()).isPresent()) {
            log.error("Student with email {} already exists", studentRequestDTO.email());
            throw new DuplicateResourceException("Student with email " + studentRequestDTO.email() + " already exists!");
        }
        Student student = studentMapper.toEntity(studentRequestDTO);
        Student savedStudent = studentRepository.save(student);
        log.info("Student saved with ID: {}", savedStudent.getId());
        return studentMapper.toDto(savedStudent);
    }

    @Override
    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO studentRequestDTO) {
        log.info("Updating student with ID: {}", id);
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Student with ID {} not found", id);
                    return new EntityNotFoundException("Student not found with ID: " + id);
                });
        existingStudent.setEmail(studentRequestDTO.email());
        existingStudent.setFirstName(studentRequestDTO.firstName());
        existingStudent.setLastName(studentRequestDTO.lastName());
        // Update other fields as needed
        Student updatedStudent = studentRepository.save(existingStudent);
        log.info("Student updated with ID: {}", updatedStudent.getId());
        return studentMapper.toDto(updatedStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        log.info("Deleting student with ID: {}", id);
        if (!studentRepository.existsById(id)) {
            log.error("Student with ID {} not found", id);
            throw new EntityNotFoundException("Student not found with ID: " + id);
        }
        studentRepository.deleteById(id);
        log.info("Student deleted with ID: {}", id);
    }

    @Override
    public Optional<StudentResponseDTO> getStudentById(Long id) {
        log.info("Fetching student with ID: {}", id);
        return studentRepository.findById(id)
                .map(studentMapper::toDto);
    }

    @Override
    public List<StudentResponseDTO> getAllStudents() {
        log.info("Fetching all students");
        return studentRepository.findAll().stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponseDTO findByEmail(String email) {
        log.info("Fetching student with email: {}", email);
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Student not found with email: " + email));
        return studentMapper.toDto(student);
    }

    @Override
    public List<StudentResponseDTO> findByLastName(String lastName) {
        log.info("Fetching students with last name: {}", lastName);
        return studentRepository.findByLastName(lastName).stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentResponseDTO> findBySessionId(Long sessionId) {
        log.info("Fetching students for session ID: {}", sessionId);
        return studentRepository.findByAttendances_Session_Id(sessionId).stream()
                .map((student) -> {
                    AttendanceResponseDTO attendanceResponseDTO = attendanceService.findByStudentIdAndSessionId(sessionId, sessionId);
                    return new StudentResponseDTO(
                            student.getId(),
                            student.getFirstName(),
                            student.getLastName(),
                            student.getEmail(),
                            attendanceResponseDTO
                    );
                })
                .collect(Collectors.toList());
    }
}

