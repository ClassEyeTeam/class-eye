package com.classeye.studentservice.dto.response;

/**
 * @author moham
 **/
public record StudentResponseDTO(
        Long id,
        String firstName,
        String lastName,
        String email
) {}

