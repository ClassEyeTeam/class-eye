package com.classeye.classservice.dto.request;

import com.classeye.classservice.entity.SalleType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

/**
 * @author Najat
 */
public record SalleCreateDTO(
        @NotEmpty(message = "Name cannot be empty")
        @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
        String name,
        @Min(value = 0, message = "Capacity must be 0 or higher")
        int capacity,
        SalleType salleType,
        long blockId
) {
}
