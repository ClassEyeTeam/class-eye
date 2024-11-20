package com.classeye.universityservice.dto.response;

import com.classeye.universityservice.dto.ModuleDTO;
import com.classeye.universityservice.dto.OptionDTO;
import com.classeye.universityservice.dto.TeacherDTO;

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
