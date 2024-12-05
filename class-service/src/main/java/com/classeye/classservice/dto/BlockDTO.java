package com.classeye.classservice.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * @author Najat
 */
public record BlockDTO( Long id,
                        String name,
                        String description,
                        int roomCount,
                       List<SalleDTO> salles
                        )
{

}
