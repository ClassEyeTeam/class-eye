package com.classeye.universityservice.mapper;

import com.classeye.universityservice.dto.DepartmentDto;
import com.classeye.universityservice.dto.ModuleDTO;
import com.classeye.universityservice.dto.OptionDTO;
import com.classeye.universityservice.dto.response.OptionModulesDTO;
import com.classeye.universityservice.dto.response.OptionResponseDto;
import com.classeye.universityservice.entity.Department;
import com.classeye.universityservice.entity.ModuleOption;
import com.classeye.universityservice.entity.Option;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OptionMapper {
    OptionMapper INSTANCE = Mappers.getMapper(OptionMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "department.id", source = "departmentId")
    Option toEntity(OptionDTO optionDTO);


//    @Mapping(target = "modules", expression = "java(mapModuleOptionsToModules(option.getModuleOptions()))")
    @Mapping(target = "department", expression = "java(mapDepartmentToDepartmentDto(option.getDepartment()))")
    OptionResponseDto toDto(Option option);


    default DepartmentDto mapDepartmentToDepartmentDto(Department department) {
        return new DepartmentDto(
                department.getId(),
                department.getName(),
                department.getDescription()
        );
    }

    default List<ModuleDTO> mapModuleOptionsToModules(List<ModuleOption> moduleOptions) {
        return moduleOptions.stream()
                .map(option -> new ModuleDTO(
                        option.getModule().getId(),
                        option.getModule().getName(),
                        option.getModule().getDescription()
                ))
                .collect(Collectors.toList());
    }
}