package com.classeye.universityservice.dto.response;

import com.classeye.universityservice.dto.DepartmentDto;
import com.classeye.universityservice.entity.Department;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * @author sejja
 **/
public record OptionResponseDto(
        long id,
        @NotEmpty(message = "Name cannot be Empty")
        @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
        String name,
        String description,
        DepartmentDto department
//        List<Long> moduleOptionIds
) {
}
