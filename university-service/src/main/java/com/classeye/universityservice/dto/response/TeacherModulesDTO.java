package com.classeye.universityservice.dto.response;

import com.classeye.universityservice.dto.DepartmentDto;
import com.classeye.universityservice.dto.ModuleDTO;

import java.util.List;

/**
 * @author sejja
 **/
public record TeacherModulesDTO (
            Long id,
            String name,
            String email,
            String phone,
            String address,
            DepartmentDto department,
            List<ModuleDTO> modules
    ) {}
