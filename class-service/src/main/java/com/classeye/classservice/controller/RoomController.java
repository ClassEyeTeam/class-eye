package com.classeye.classservice.controller;


import com.classeye.classservice.dto.request.RoomRequestDTO;
import com.classeye.classservice.dto.response.RoomResponseDTO;
import com.classeye.classservice.service.RoomService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Najat
 */
@RestController
@RequestMapping("/rooms")
@AllArgsConstructor
public class RoomController {
    private final RoomService roomService;

    // Create a new salle
    @PostMapping
    public ResponseEntity<RoomResponseDTO> createRoom(@Valid @RequestBody RoomRequestDTO roomRequestDTO) {
        RoomResponseDTO createdSalle = roomService.createRoom(roomRequestDTO);
        return new ResponseEntity<>(createdSalle, HttpStatus.CREATED);
    }

    // Update an existing salle
    @PutMapping("/{id}")
    public ResponseEntity<RoomResponseDTO> updateRoom(@PathVariable long id, @RequestBody @Valid RoomRequestDTO roomRequestDTO) {
        RoomResponseDTO updatedSalle = roomService.updateRoom(roomRequestDTO, id);
        return ResponseEntity.ok(updatedSalle);
    }

    // Delete a salle by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }

    // Get a salle by ID
    @GetMapping("/{id}")
    public ResponseEntity<RoomResponseDTO> getRoomById(@PathVariable long id) {
        RoomResponseDTO salleDTO = roomService.getRoomDtoById(id);
        return ResponseEntity.ok(salleDTO);
    }

    @GetMapping
    public ResponseEntity<List<RoomResponseDTO>> getAllRooms() {
        List<RoomResponseDTO> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }

}
