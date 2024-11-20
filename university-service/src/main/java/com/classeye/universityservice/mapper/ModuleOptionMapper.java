package com.classeye.universityservice.mapper;


import com.classeye.universityservice.dto.request.ModuleOptionRequestDTO;
import com.classeye.universityservice.dto.response.ModuleOptionResponseDTO;
import com.classeye.universityservice.entity.ModuleOption;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author sejja
 **/
@Mapper(componentModel = "spring")
public interface ModuleOptionMapper {
    ModuleOptionMapper INSTANCE = Mappers.getMapper(ModuleOptionMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "teacher.id", source = "teacherId")
    @Mapping(target = "module.id", source = "moduleId")
    @Mapping(target = "option.id", source = "optionId")
    ModuleOption toEntity(ModuleOptionRequestDTO moduleOptionRequestDTO);
    ModuleOptionResponseDTO toResponseDot(ModuleOption moduleOption);
    ModuleOption toEntity(ModuleOptionResponseDTO moduleOptionResponseDTO);
    ModuleOptionResponseDTO toDto(ModuleOption moduleOption);
}