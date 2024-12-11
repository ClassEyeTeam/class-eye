package com.classeye.classservice.service;

import com.classeye.classservice.dto.request.RoomRequestDTO;
import com.classeye.classservice.dto.response.RoomResponseDTO;
import com.classeye.classservice.entity.Room;

import java.util.List;

/**
 * @author Najat
 */
public interface RoomService {
    RoomResponseDTO createRoom(RoomRequestDTO roomRequestDTO);

    RoomResponseDTO updateRoom(RoomRequestDTO roomRequestDTO, Long id);

    Room getRoomById(Long id);
    RoomResponseDTO getRoomDtoById(Long id);

    void deleteRoom(Long id);

    List<RoomResponseDTO> getAllRooms();
}
