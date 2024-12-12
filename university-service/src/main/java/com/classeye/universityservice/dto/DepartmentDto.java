package com.classeye.universityservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * @author moham
 **/
public record DepartmentDto(
        long id,
        @NotEmpty(message = "Name cannot be Empty")
        @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
        String name,
        String description) {
}
