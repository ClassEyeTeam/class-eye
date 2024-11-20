package com.classeye.universityservice.service.impl;

import com.classeye.universityservice.dto.DepartmentDto;
import com.classeye.universityservice.entity.Department;
import com.classeye.universityservice.exception.DuplicateResourceException;
import com.classeye.universityservice.mapper.DepartmentMapper;
import com.classeye.universityservice.repository.DepartmentRepository;
import com.classeye.universityservice.service.DepartmentService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author moham
 **/

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {
        log.info("Creating department with name: {}", departmentDto.name());
        if (departmentRepository.getDepartmentByName(departmentDto.name()).isPresent()) {
            log.error("Department with name {} already exists", departmentDto.name());
            throw new DuplicateResourceException("Department with name " + departmentDto.name() + " already exists!");
        }
        Department department = departmentMapper.ToEntity(departmentDto);
        Department savedDepartment = departmentRepository.save(department);
        log.info("Department created with ID: {}", savedDepartment.getId());
        return departmentMapper.ToDto(savedDepartment);
    }

    @Override
    public DepartmentDto updateDepartment(long id, DepartmentDto departmentDto) {
        log.info("Updating department with ID: {}", id);
        Department existingDepartment = getDepartmentById(id);
        existingDepartment.setName(departmentDto.name());
        existingDepartment.setDescription(departmentDto.description());
        Department updatedDepartment = departmentRepository.save(
                existingDepartment);
        log.info("Department updated with ID: {}", updatedDepartment.getId());
        return departmentMapper.ToDto(updatedDepartment);
    }

    @Override
    public void deleteDepartment(long id) {
        log.info("Deleting department with ID: {}", id);
        departmentRepository.existsById(id);

        departmentRepository.deleteById(id);
        log.info("Department deleted with ID: {}", id);
    }

    @Override
    public DepartmentDto getDepartmentDtoById(long id) {
        log.info("Fetching department with ID: {}", id);
        Department department = departmentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Department with ID {} not found", id);
                    return new EntityNotFoundException("Department not found with id: " + id);
                });
        return departmentMapper.ToDto(department);
    }

    @Override
    public Department getDepartmentById(long id) {
        log.info("Fetching department with ID: {}", id);
        return departmentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Department with ID {} not found", id);
                    return new EntityNotFoundException("Department not found with id: " + id);
                });
    }


    @Override
    public List<DepartmentDto> getAllDepartments() {
        log.info("Fetching all departments");
        return departmentRepository.findAll().stream()
                .map(departmentMapper::ToDto)
                .collect(Collectors.toList());
    }
}
