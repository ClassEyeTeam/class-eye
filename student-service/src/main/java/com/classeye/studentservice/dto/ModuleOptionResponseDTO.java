package com.classeye.studentservice.dto;

/**
 * @author sejja
 **/
public record ModuleOptionResponseDTO(
     Long id,
     ModuleDTO module,
     OptionDTO option,
     TeacherDTO teacher
){
}
