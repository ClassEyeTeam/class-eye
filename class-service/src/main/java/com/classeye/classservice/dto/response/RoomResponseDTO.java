package com.classeye.classservice.dto.response;

import com.classeye.classservice.entity.RoomType;


/**
 * @author Najat
 */
public record RoomResponseDTO(
        Long id,
        String name,
        int capacity,
        RoomType roomType,
        BlockResponseDTO block )
{

}
