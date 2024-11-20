package com.classeye.universityservice.dto.request;

import jakarta.validation.constraints.NotNull;

/**
 * @author moham
 **/
public record ModuleOptionRequestDTO(
        @NotNull(message = "Module ID cannot be null")
        Long moduleId,
        @NotNull(message = "Option ID cannot be null")
        Long optionId,
        @NotNull(message = "Teacher ID cannot be null")
        Long teacherId
) {
}
