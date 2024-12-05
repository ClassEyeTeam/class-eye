package com.classeye.classservice.service.impl;

import com.classeye.classservice.dto.BlockDTO;
import com.classeye.classservice.entity.Block;
import com.classeye.classservice.entity.Salle;
import com.classeye.classservice.mapper.BlockMapper;
import com.classeye.classservice.repository.BlockRepository;
import com.classeye.classservice.service.BlockService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Najat
 */
@Service
@Slf4j
@AllArgsConstructor
public class BlockServiceImpl  implements BlockService {

    private final BlockRepository blockRepository;
    private final BlockMapper blockMapper;

    // Implementation of the createBlock method
    @Override
    public BlockDTO createBlock(BlockDTO blockDTO){

        //verifier si le block exist deja
        blockRepository.findByName(blockDTO.name())
                .ifPresent(block -> {
                    throw new IllegalStateException("Block already exists");
                });

        Block block = blockMapper.toBlock(blockDTO);
        Block savedBlock = blockRepository.save(block);

        return blockMapper.toBlockDTO(savedBlock);
    }

    // Implementation of the updateBlock method
    @Override
    public BlockDTO updateBlock(BlockDTO blockDTO, Long id){
        Block block= blockRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Block not found"));
        block.setName(blockDTO.name());
        block.setDescription(blockDTO.description());
        block.setRoomCount(blockDTO.roomCount());
       //block.setSalles(blockDTO.salles());
        Block updatedBlock = blockRepository.save(block);
        return blockMapper.toBlockDTO(updatedBlock);
    }

    // Implementation of the deleteBlock method
    @Override
    public void deleteBlock(Long id){
        Block block = blockRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Block not found"));
        blockRepository.delete(block);
    }

    // Implementation of the getBlock method
    @Override
    public BlockDTO getBlock(Long id){
        Block block = blockRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(("Block not found")));
        return blockMapper.toBlockDTO(block);
    }

    // Implementation of the getAllBlocks method
    @Override
    public List<BlockDTO> getAllBlocks(){
        return blockRepository.findAll().stream() // stream() is a method that converts the list into a Stream wich means that we can apply operations on it
                .map(blockMapper::toBlockDTO)
                .toList();
    }

    // Implementation of the getBlockSalles method
    @Override
    public List<String> getBlockSalles(Long id ){
        Block block = blockRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException(("Block not found")));

        List<Salle> salles = block.getSalles();
        return salles.stream()
                .map(Salle::getName)
                .toList();
    }

}
