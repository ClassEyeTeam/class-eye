package com.classeye.universityservice.dto.response;

import com.classeye.universityservice.dto.DepartmentDto;

/**
 * @author moham
 **/

public record TeacherResponseDTO(
        long id,
        String name,
        String email,
        String phone,
        String address,
        DepartmentDto department
) {
}
