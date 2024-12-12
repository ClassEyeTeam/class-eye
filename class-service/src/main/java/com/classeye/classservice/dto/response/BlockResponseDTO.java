package com.classeye.classservice.dto.response;

import com.classeye.classservice.entity.Room;

import java.util.List;

/**
 * @author Najat
 */
public record BlockResponseDTO(Long id,
                               String name,
                               String description,
                               List<RoomResponseDTO> classes
) {
}
