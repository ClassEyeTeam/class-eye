package com.classeye.classservice.dto.response;

import com.classeye.classservice.dto.SalleDTO;

import java.util.List;

/**
 * @author Najat
 */
public record BlockResponseDTO(Long id,
                                String name,
                                String description,
                                int roomCount,
                               List<SalleDTO> salles
) {
}
