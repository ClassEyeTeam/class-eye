package com.classeye.classservice.dto.response;

import com.classeye.classservice.dto.BlockDTO;
import com.classeye.classservice.entity.SalleType;

/**
 * @author Najat
 */
public record SalleResponseDTO(
        Long id,
        String name,
        int capacity,
        SalleType salleType,
        BlockDTO block )
{

}
