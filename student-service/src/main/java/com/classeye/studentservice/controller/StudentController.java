package com.classeye.studentservice.controller;

/**
 * @author moham
 **/
import com.classeye.studentservice.dto.request.StudentRequestDTO;
import com.classeye.studentservice.dto.response.StudentResponseDTO;
import com.classeye.studentservice.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(@Valid @RequestBody StudentRequestDTO studentRequestDTO) {
        StudentResponseDTO createdStudent = studentService.saveStudent(studentRequestDTO);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable Long id, @Valid @RequestBody StudentRequestDTO studentRequestDTO) {
        StudentResponseDTO updatedStudent = studentService.updateStudent(id, studentRequestDTO);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable Long id) {
        Optional<StudentResponseDTO> student = studentService.getStudentById(id);
        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents() {
        List<StudentResponseDTO> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<StudentResponseDTO> getStudentByEmail(@PathVariable String email) {
        StudentResponseDTO student = studentService.findByEmail(email);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/lastname/{lastName}")
    public ResponseEntity<List<StudentResponseDTO>> getStudentsByLastName(@PathVariable String lastName) {
        List<StudentResponseDTO> students = studentService.findByLastName(lastName);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/session/{sessionId}")
    public ResponseEntity<List<StudentResponseDTO>> getStudentsBySessionId(@PathVariable Long sessionId) {
        List<StudentResponseDTO> students = studentService.findBySessionId(sessionId);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/option/{optionId}")
    public ResponseEntity<List<StudentResponseDTO>> getStudentsByOptionId(@PathVariable Long optionId) {
        List<StudentResponseDTO> students = studentService.findByOptionId(optionId);
        return ResponseEntity.ok(students);
    }
}
