package com.classeye.classservice.controller;

import com.classeye.classservice.dto.BlockDTO;
import com.classeye.classservice.entity.Block;
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
public class BlockContoller {
    private final BlockService blockService;

    // Create a new block
    @PostMapping
    public ResponseEntity<BlockDTO> createBlock(@Valid @RequestBody BlockDTO blockDTO) {
        BlockDTO createdBlock = blockService.createBlock(blockDTO);
        return new ResponseEntity<>(createdBlock, HttpStatus.CREATED);
    }

    // Update an existing block
    @PutMapping("/{id}")
    public ResponseEntity<BlockDTO> updateBlock(@PathVariable long id, @RequestBody @Valid BlockDTO blockDTO){
        BlockDTO updatedBlock = blockService.updateBlock(blockDTO, id);
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
    public ResponseEntity<BlockDTO> getBlockById(@PathVariable long id){
        BlockDTO blockDTO = blockService.getBlock(id);
        return ResponseEntity.ok(blockDTO);
    }

    // Get a list of all blocks
    @GetMapping
    public ResponseEntity<List<BlockDTO>> getAllBlocks(){
        List<BlockDTO> blocks = blockService.getAllBlocks();
        return ResponseEntity.ok(blocks);
    }
}
