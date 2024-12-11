package com.classeye.classservice.dto.request;

import com.classeye.classservice.dto.ValidEnum;
import com.classeye.classservice.entity.RoomType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * @author Najat
 */
public record RoomRequestDTO(
        Long id,
        @NotEmpty(message = "Name cannot be empty !")
        @Size(min = 1, max = 50, message = "Name must be between 1 and 50 characters")
        String name,

        @Min(value = 0, message = "Capacity must be 0 or higher")
        int capacity,

        @NotNull(message = "Room type cannot be null")
        @ValidEnum(enumClass = RoomType.class, ignoreCase = true, message = "Invalid room type. Allowed values are AMPHITHEATER, CLASSROOM.")
        RoomType roomType,

        @NotNull(message = "blockId is required !")
        long blockId
) {
}
