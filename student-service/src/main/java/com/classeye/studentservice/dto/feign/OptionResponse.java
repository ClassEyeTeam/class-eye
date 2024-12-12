package com.classeye.studentservice.dto.feign;

/**
 * @author sejja
 **/
public record OptionResponse(
        Long id,
        String name,
        String description,
        Long moduleId
) {
}
