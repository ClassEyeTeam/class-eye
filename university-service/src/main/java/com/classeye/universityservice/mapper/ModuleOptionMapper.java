package com.classeye.universityservice.mapper;


import com.classeye.universityservice.dto.ModuleDTO;
import com.classeye.universityservice.dto.OptionDTO;
import com.classeye.universityservice.dto.TeacherDTO;
import com.classeye.universityservice.dto.request.ModuleOptionRequestDTO;
import com.classeye.universityservice.dto.response.ModuleOptionResponseDTO;
import com.classeye.universityservice.entity.Module;
import com.classeye.universityservice.entity.ModuleOption;
import com.classeye.universityservice.entity.Option;
import com.classeye.universityservice.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author sejja
 **/
@Mapper(componentModel = "spring", uses = {OptionMapper.class, ModuleMapper.class, TeacherMapper.class})

public interface ModuleOptionMapper {
    ModuleOptionMapper INSTANCE = Mappers.getMapper(ModuleOptionMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "teacher.id", source = "teacherId")
    @Mapping(target = "module.id", source = "moduleId")
    @Mapping(target = "option.id", source = "optionId")
    ModuleOption toEntity(ModuleOptionRequestDTO moduleOptionRequestDTO);
    ModuleOptionResponseDTO toDto(ModuleOption moduleOption);


}