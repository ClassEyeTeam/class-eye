package com.classeye.classservice.mapper;


import com.classeye.classservice.dto.request.RoomRequestDTO;
import com.classeye.classservice.dto.response.RoomResponseDTO;
import com.classeye.classservice.entity.Room;
import org.mapstruct.Mapper;

/**
 * @author Najat
 */
@Mapper(componentModel = "spring")
public interface RoomMapper {
    RoomResponseDTO toRoomDTO(Room room);
    Room toRoom(RoomRequestDTO roomRequestDTO);
}
