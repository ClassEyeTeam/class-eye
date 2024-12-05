package com.classeye.classservice.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


/**
 * @author Najat
 */
public record BlockCreateDTO(
        @NotEmpty( message = "Name cannot be empty")
        @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
        String name,
        String description,
        @Min(value = 0, message = "Room count must be 0 or higher")
        int roomCount
) {
}
