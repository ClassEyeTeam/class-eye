package com.classeye.universityservice.mapper;

import com.classeye.universityservice.dto.ModuleDTO;
import com.classeye.universityservice.dto.request.TeacherCreateDTO;
import com.classeye.universityservice.dto.response.TeacherModulesDTO;
import com.classeye.universityservice.dto.response.TeacherResponseDTO;
import com.classeye.universityservice.entity.ModuleOption;
import com.classeye.universityservice.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "department.id", source = "departmentId")
    Teacher toEntity(TeacherCreateDTO teacherCreateDTO);

    TeacherResponseDTO toResponseDto(Teacher teacher);

    Teacher toEntity(TeacherResponseDTO teacherDTO);

    TeacherResponseDTO toDto(Teacher teacher);

    @Mapping(target = "modules", expression = "java(mapModuleOptionsToModules(teacher.getModuleOptions()))")
    TeacherModulesDTO toModulesDto(Teacher teacher);

    default List<ModuleDTO> mapModuleOptionsToModules(List<ModuleOption> moduleOptions) {
        return moduleOptions.stream()
                .map(option -> new ModuleDTO(
                        option.getModule().getId(),
                        option.getModule().getName(),null
                ))
                .collect(Collectors.toList());
    }

    void updateTeacherFromDto(TeacherCreateDTO teacherCreateDTO, @MappingTarget Teacher teacher);
}