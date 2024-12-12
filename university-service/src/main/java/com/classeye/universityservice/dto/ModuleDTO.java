package com.classeye.universityservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/**
 * @author sejja
 **/
public record ModuleDTO(
        long id,
        @NotEmpty(message = "Name cannot be Empty")
        @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
        String name,
        String description) {

}
