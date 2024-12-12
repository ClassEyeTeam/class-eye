package com.classeye.classservice.controller;

import com.classeye.classservice.dto.request.BlockRequestDTO;
import com.classeye.classservice.dto.response.BlockResponseDTO;
import com.classeye.classservice.dto.response.RoomResponseDTO;
import com.classeye.classservice.service.BlockService;
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
@RequestMapping("/blocks")
@AllArgsConstructor
public class BlockController {
    private final BlockService blockService;

    @PostMapping
    public ResponseEntity<BlockResponseDTO> createBlock(@Valid @RequestBody BlockRequestDTO blockRequestDTO) {
        BlockResponseDTO createdBlock = blockService.createBlock(blockRequestDTO);
        return new ResponseEntity<>(createdBlock, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlockResponseDTO> updateBlock(@PathVariable long id, @RequestBody @Valid BlockRequestDTO blockRequestDTO){
        BlockResponseDTO updatedBlock = blockService.updateBlock(blockRequestDTO, id);
        return ResponseEntity.ok(updatedBlock);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlock(@PathVariable long id){
        blockService.deleteBlock(id);
        return ResponseEntity.noContent().build(); // noContent() returns a 204 No Content status code response to the client indicating that the request has succeeded, but the response body contains no information.
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlockResponseDTO> getBlockById(@PathVariable long id){
        BlockResponseDTO blockDTO = blockService.getBlockDtoById(id);
        return ResponseEntity.ok(blockDTO);
    }

    @GetMapping
    public ResponseEntity<List<BlockResponseDTO>> getAllBlocks(){
        List<BlockResponseDTO> blocks = blockService.getAllBlocks();
        return ResponseEntity.ok(blocks);
    }

    public ResponseEntity<List<RoomResponseDTO>> getBlockRooms(@PathVariable long id){
        List<RoomResponseDTO> rooms = blockService.getBlockRooms(id);
        return ResponseEntity.ok(rooms);
    }
}
