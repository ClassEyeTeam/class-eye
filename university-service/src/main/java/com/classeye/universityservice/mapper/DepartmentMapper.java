package com.classeye.universityservice.mapper;

import com.classeye.universityservice.dto.DepartmentDto;
import com.classeye.universityservice.entity.Department;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author moham
 **/
@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    Department ToEntity(DepartmentDto departmentDto);
    DepartmentDto ToDto(Department department);
}
