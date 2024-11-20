package com.classeye.universityservice.service;

import com.classeye.universityservice.dto.DepartmentDto;
import com.classeye.universityservice.entity.Department;

import java.util.List;

/**
 * @author moham
 **/
public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentDto departmentDto);
    DepartmentDto updateDepartment(long id, DepartmentDto departmentDto);
    void deleteDepartment(long id);
    DepartmentDto getDepartmentDtoById(long id);
    Department getDepartmentById(long id);
    List<DepartmentDto> getAllDepartments();
}
