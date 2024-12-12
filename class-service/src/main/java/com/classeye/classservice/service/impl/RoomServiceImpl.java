package com.classeye.classservice.service.impl;


import com.classeye.classservice.dto.request.RoomRequestDTO;
import com.classeye.classservice.dto.response.RoomResponseDTO;
import com.classeye.classservice.entity.Block;
import com.classeye.classservice.entity.Room;
import com.classeye.classservice.exception.DuplicateResourceException;
import com.classeye.classservice.mapper.RoomMapper;
import com.classeye.classservice.repository.RoomRepository;
import com.classeye.classservice.service.BlockService;
import com.classeye.classservice.service.RoomService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import java.util.List;

/**
 * @author Najat
 */
@Service
@Slf4j
@AllArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;
    private final BlockService blockService;


    @Override
    public RoomResponseDTO createRoom(RoomRequestDTO roomRequestDTO) {
        log.info("Creating room: {}", roomRequestDTO);

        // Retrieve the block using blockId from the request
        Block block = blockService.getBlockById(roomRequestDTO.blockId());
        log.info("Retrieved block: {}", block);

        // Check if a room with the same name already exists in the block
        roomRepository.findByNameAndBlockId(roomRequestDTO.name(), block.getId())
                .ifPresent(room -> {
                    log.error("Room with name {} already exists in Block {}", roomRequestDTO.name(), block.getName());
                    throw new DuplicateResourceException(
                            "Room with name " + roomRequestDTO.name() + " already exists in Block " + block.getName());
                });

        // Convert DTO to Room entity and set the block
        Room room = roomMapper.toRoom(roomRequestDTO);
        room.setBlock(block); // Ensure the block is associated with the room

        // Save the room in the repository
        room = roomRepository.save(room);
        log.info("Room created: {}", room);

        // Return the response DTO
        return roomMapper.toRoomDTO(room);
    }

    @Override
    public RoomResponseDTO updateRoom(RoomRequestDTO roomRequestDTO, Long id) {
        log.info("Updating room with ID: {}", id);

        // Retrieve the room by ID
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Room with ID {} not found", id);
                    return new EntityNotFoundException("Room not found with id: " + id);
                });

        // Retrieve the block using blockId from the request
        Block block = blockService.getBlockById(roomRequestDTO.blockId());
        log.info("Retrieved block: {}", block);

        // Check if a room with the same name already exists in the block
        roomRepository.findByNameAndBlockId(roomRequestDTO.name(), block.getId())
                .filter(existingRoom -> !existingRoom.getId().equals(id)) // Ensure it's not the same room
                .ifPresent(existingRoom -> {
                    log.error("Room with name {} already exists in Block {}", roomRequestDTO.name(), block.getName());
                    throw new DuplicateResourceException(
                            "Room with name " + roomRequestDTO.name() + " already exists in Block " + block.getName());
                });

        // Update the room entity with the new values from the request
        room.setName(roomRequestDTO.name());
        room.setCapacity(roomRequestDTO.capacity());
        room.setRoomType(roomRequestDTO.roomType());
        room.setBlock(block); // Ensure the block is associated with the room

        // Save the updated room in the repository
        Room updatedRoom = roomRepository.save(room);
        log.info("Room updated: {}", updatedRoom);

        // Return the response DTO
        return roomMapper.toRoomDTO(updatedRoom);
    }

    @Override
    public Room getRoomById(Long id) {
        log.info("Fetching room with ID: {}", id);
        return roomRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Room with ID {} not found", id);
                    return new EntityNotFoundException("Room not found with id: " + id);
                });
    }

    @Override
    public RoomResponseDTO getRoomDtoById(Long id) {
        log.info("Fetching room DTO with ID: {}", id);
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Room with ID {} not found", id);
                    return new EntityNotFoundException("Room not found with id: " + id);
                });
        return roomMapper.toRoomDTO(room);
    }

    @Override
    public void deleteRoom(Long id) {
        log.info("Deleting room with ID: {}", id);
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Room with ID {} not found", id);
                    return new EntityNotFoundException("Room not found with id: " + id);
                });

        roomRepository.delete(room);
        log.info("Room with ID {} deleted", id);
    }

    @Override
    public List<RoomResponseDTO> getAllRooms() {
        log.info("Fetching all rooms");
        List<RoomResponseDTO> rooms = roomRepository.findAll().stream()
                .map(roomMapper::toRoomDTO)
                .toList();
        log.info("Total rooms fetched: {}", rooms.size());
        return rooms;
    }
}