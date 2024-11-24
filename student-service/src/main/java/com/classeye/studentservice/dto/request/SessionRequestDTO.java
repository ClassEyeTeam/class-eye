package com.classeye.studentservice.dto.request;

/**
 * @author moham
 **/

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record SessionRequestDTO(
        @NotNull(message = "Option ID cannot be null")
        Long optionId,

        @NotNull(message = "Start date and time cannot be null")
        LocalDateTime startDateTime,

        @NotNull(message = "End date and time cannot be null")
        LocalDateTime endDateTime
) {}

