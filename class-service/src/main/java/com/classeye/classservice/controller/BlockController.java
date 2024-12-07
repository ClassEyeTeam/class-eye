package com.classeye.classservice.controller;

import com.classeye.classservice.dto.BlockDTO;
import com.classeye.classservice.dto.SalleDTO;
import com.classeye.classservice.dto.request.BlockCreateDTO;
import com.classeye.classservice.dto.response.BlockResponseDTO;
import com.classeye.classservice.dto.response.SalleResponseDTO;
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

    // Create a new block
    @PostMapping
    public ResponseEntity<BlockResponseDTO> createBlock(@Valid @RequestBody BlockCreateDTO blockCreateDTO) {
        BlockResponseDTO createdBlock = blockService.createBlock(blockCreateDTO);
        return new ResponseEntity<>(createdBlock, HttpStatus.CREATED);
    }

    // Update an existing block
    @PutMapping("/{id}")
    public ResponseEntity<BlockResponseDTO> updateBlock(@PathVariable long id, @RequestBody @Valid BlockCreateDTO blockCreateDTO){
        BlockResponseDTO updatedBlock = blockService.updateBlock(blockCreateDTO, id);
        return ResponseEntity.ok(updatedBlock);
    }

    // Delete a block by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlock(@PathVariable long id){
        blockService.deleteBlock(id);
        return ResponseEntity.noContent().build(); // noContent() returns a 204 No Content status code response to the client indicating that the request has succeeded, but the response body contains no information.
    }

    // Get a block by ID
    @GetMapping("/{id}")
    public ResponseEntity<BlockResponseDTO> getBlockById(@PathVariable long id){
        BlockResponseDTO blockDTO = blockService.getBlock(id);
        return ResponseEntity.ok(blockDTO);
    }

    // Get a list of all blocks
    @GetMapping
    public ResponseEntity<List<BlockResponseDTO>> getAllBlocks(){
        List<BlockResponseDTO> blocks = blockService.getAllBlocks();
        return ResponseEntity.ok(blocks);
    }

    // Get a list of all salles in a block
    public ResponseEntity<List<SalleResponseDTO>> getBlockSalles(@PathVariable long id){
        List<SalleResponseDTO> salles = blockService.getBlockSalles(id);
        return ResponseEntity.ok(salles);
    }
}
