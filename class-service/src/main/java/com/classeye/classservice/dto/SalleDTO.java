package com.classeye.classservice.dto;

import com.classeye.classservice.entity.SalleType;

/**
 * @author Najat
 */
public record SalleDTO(
        Long id,
        String name,
        int capacity,
        SalleType salleType,
        BlockDTO block
) {
}
