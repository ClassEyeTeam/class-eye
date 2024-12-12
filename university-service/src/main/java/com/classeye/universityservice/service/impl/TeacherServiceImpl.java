package com.classeye.universityservice.service.impl;

import com.classeye.universityservice.dto.TeacherDTO;
import com.classeye.universityservice.dto.request.TeacherCreateDTO;
import com.classeye.universityservice.dto.response.TeacherModulesDTO;
import com.classeye.universityservice.dto.response.TeacherResponseDTO;
import com.classeye.universityservice.entity.Department;
import com.classeye.universityservice.entity.Teacher;
import com.classeye.universityservice.feign.AuthFeignClient;
import com.classeye.universityservice.mapper.DepartmentMapper;
import com.classeye.universityservice.mapper.TeacherMapper;
import com.classeye.universityservice.repository.TeacherRepository;
import com.classeye.universityservice.service.DepartmentService;
import com.classeye.universityservice.service.TeacherService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sejja
 **/
@Service
@Slf4j
@AllArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final AuthFeignClient authFeignClient;
    private final TeacherMapper teacherMapper;
    private final DepartmentMapper departmentMapper;
    private final DepartmentService departmentService;

    @Override
    public TeacherResponseDTO createTeacher(TeacherCreateDTO teacherDTO) {
        log.info("Creating Teacher with name: {} in Department ID: {}", teacherDTO.name(), teacherDTO.departmentId());

        // Validate related Department
        Department department = departmentService.getDepartmentById(teacherDTO.departmentId());

        // Save Teacher entity
        Teacher teacher = teacherMapper.toEntity(teacherDTO);
        Teacher savedTeacher = teacherRepository.save(teacher);

        log.info("Teacher created with ID: {}", savedTeacher.getId());
        authFeignClient.createUser( savedTeacher.getEmail(), "teacher");
        log.info(
                "User created with email: {} and role: {}",
                savedTeacher.getEmail(),
                "teacher"
        );
        return new TeacherResponseDTO(
                savedTeacher.getId(),
                savedTeacher.getName(),
                savedTeacher.getEmail(),
                savedTeacher.getPhone(),
                savedTeacher.getAddress(),
                departmentMapper.ToDto(department)
        );
    }

    @Override
    public TeacherResponseDTO updateTeacher(Long id, TeacherCreateDTO teacherDTO) {
        log.info("Updating Teacher with ID: {}", id);

        // Validate Teacher existence
        Teacher existingTeacher = getTeacherById(id);

        // Validate related Department and map data
        mapUpdatedTeacher(existingTeacher, teacherDTO);
// Validate related Department
        Department department = departmentService.getDepartmentById(teacherDTO.departmentId());
        Teacher savedTeacher = teacherRepository.save(existingTeacher);


        log.info("Teacher updated with ID: {}", savedTeacher.getId());

        return new TeacherResponseDTO(
                savedTeacher.getId(),
                savedTeacher.getName(),
                savedTeacher.getEmail(),
                savedTeacher.getPhone(),
                savedTeacher.getAddress(),
                departmentMapper.ToDto(department)
        );
    }

    @Override
    public void deleteTeacher(Long id) {
        log.info("Deleting Teacher with ID: {}", id);
        getTeacherById(id); //just to check teacher existence !
        teacherRepository.deleteById(id);
        log.info("Teacher deleted with ID: {}", id);
    }

    @Override
    @Transactional
    public TeacherModulesDTO getTeacherDtoById(Long id) {
        log.info("Fetching Teacher DTO with ID: {}", id);
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> {
            log.error("Teacher with ID {} not found", id);
            return new EntityNotFoundException("Teacher not found with id: " + id);
        });
        return teacherMapper.toModulesDto(teacher);
    }

    @Override
    public Teacher getTeacherById(Long id) {
        log.info("Fetching Teacher with ID: {}", id);
        return teacherRepository.findById(id).orElseThrow(() -> {
            log.error("Teacher with ID {} not found", id);
            return new EntityNotFoundException("Teacher not found with id: " + id);
        });
    }

    @Override
    public List<TeacherResponseDTO> getAllTeachers() {
        log.info("Fetching all Teachers");
        return teacherRepository.findAll().stream()
                .map(teacherMapper::toDto)
                .collect(Collectors.toList());
    }

    private void mapUpdatedTeacher(Teacher existingTeacher, TeacherCreateDTO teacherDTO) {
        existingTeacher.setName(teacherDTO.name());
        existingTeacher.setPhone(teacherDTO.phone());
        existingTeacher.setAddress(teacherDTO.address());
        existingTeacher.setEmail(teacherDTO.email());
        existingTeacher.setDepartment(departmentService.getDepartmentById(teacherDTO.departmentId()));

    }
}

