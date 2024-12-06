package com.classeye.classservice.dto;

import java.util.List;

/**
 * @author Najat
 */
public record BlockDTO( Long id,
                        String name,
                        String description,
                        int roomCount,
                       //
                       List<SalleDTO> salles
                        )
{

}
