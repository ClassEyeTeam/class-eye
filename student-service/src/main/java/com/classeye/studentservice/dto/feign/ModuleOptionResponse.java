package com.classeye.studentservice.dto.feign;

/**
 * @author sejja
 **/
public record ModuleOptionResponse(
        Long id,
        String name,
        String code,
        String description,
        Long moduleId,
        Long optionId
) {
}
