package com.classeye.studentservice.service;

/**
 * @author moham
 **/


import com.classeye.studentservice.dto.request.StudentRequestDTO;
import com.classeye.studentservice.dto.response.StudentResponseDTO;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    StudentResponseDTO saveStudent(StudentRequestDTO studentRequestDTO);
    StudentResponseDTO updateStudent(Long id,StudentRequestDTO studentRequestDTO);
    Optional<StudentResponseDTO> getStudentById(Long id);
    List<StudentResponseDTO> getAllStudents();
    void deleteStudent(Long id);

    StudentResponseDTO findByEmail(String email);
    List<StudentResponseDTO> findByLastName(String lastName);
    List<StudentResponseDTO> findBySessionId(Long sessionId);
}

