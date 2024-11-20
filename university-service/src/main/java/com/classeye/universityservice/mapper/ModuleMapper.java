package com.classeye.universityservice.mapper;

import com.classeye.universityservice.dto.ModuleDTO;
import com.classeye.universityservice.entity.Module;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author sejja
 **/
@Mapper(componentModel = "spring")
public interface ModuleMapper {
    ModuleMapper INSTANCE = Mappers.getMapper(ModuleMapper.class);

    Module toEntity(ModuleDTO moduleDTO);
    ModuleDTO toDto(Module module);
}