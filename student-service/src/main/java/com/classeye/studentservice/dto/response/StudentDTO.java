package com.classeye.studentservice.dto.response;

/**
 * @author moham
 **/
public record StudentDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        Long optionId
) {
}

