package com.classeye.classservice.service.impl;

import com.classeye.classservice.dto.request.BlockRequestDTO;
import com.classeye.classservice.dto.response.BlockResponseDTO;
import com.classeye.classservice.dto.response.RoomResponseDTO;
import com.classeye.classservice.entity.Block;
import com.classeye.classservice.entity.Room;
import com.classeye.classservice.exception.DuplicateResourceException;
import com.classeye.classservice.mapper.BlockMapper;
import com.classeye.classservice.mapper.RoomMapper;
import com.classeye.classservice.repository.BlockRepository;
import com.classeye.classservice.service.BlockService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Najat
 */
@Service
@Slf4j
@AllArgsConstructor
public class BlockServiceImpl implements BlockService {

    private final BlockRepository blockRepository;
    private final BlockMapper blockMapper;
    private final RoomMapper roomMapper;

    @Override
    public BlockResponseDTO createBlock(BlockRequestDTO blockRequestDTO) {
        log.info("Attempting to create block with name: {}", blockRequestDTO.name());

        if (blockRepository.findByName(blockRequestDTO.name()).isPresent()) {
            log.error("Block with name {} already exists", blockRequestDTO.name());
            throw new DuplicateResourceException("Block with name " + blockRequestDTO.name() + " already exists!");
        }

        Block savedBlock = blockRepository.save(blockMapper.toBlock(blockRequestDTO));
        log.info("Block with name {} successfully created", blockRequestDTO.name());

        return blockMapper.toBlockDTO(savedBlock);
    }

    @Override
    public BlockResponseDTO updateBlock(BlockRequestDTO blockRequestDTO, Long id) {
        log.info("Attempting to update block with ID: {}", id);

        Block block = getBlockById(id);

        if (blockRepository.findByName(blockRequestDTO.name()).isPresent()) {
            log.error("Block with name {} already exists", blockRequestDTO.name());
            throw new DuplicateResourceException("Block with name " + blockRequestDTO.name() + " already exists!");
        }

        block.setName(blockRequestDTO.name());
        block.setDescription(blockRequestDTO.description());
        Block updatedBlock = blockRepository.save(block);

        log.info("Block with ID {} successfully updated", id);
        return blockMapper.toBlockDTO(updatedBlock);
    }

    @Override
    public void deleteBlock(Long id) {
        log.info("Attempting to delete block with ID: {}", id);

        Block block = getBlockById(id);
        blockRepository.delete(block);

        log.info("Block with ID {} successfully deleted", id);
    }

    @Override
    public Block getBlockById(Long id) {
        log.info("Fetching block with ID: {}", id);

        return blockRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Block with ID {} not found", id);
                    return new EntityNotFoundException("Block not found with id: " + id);
                });
    }

    @Override
    public BlockResponseDTO getBlockDtoById(Long id) {
        log.info("Fetching block DTO with ID: {}", id);

        Block block = blockRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Block with ID {} not found", id);
                    return new EntityNotFoundException("Block not found with id: " + id);
                });

        log.info("Block DTO with ID {} successfully fetched", id);
        return blockMapper.toBlockDTO(block);
    }

    @Override
    public List<BlockResponseDTO> getAllBlocks() {
        log.info("Fetching all blocks");

        List<BlockResponseDTO> blocks = blockRepository.findAll().stream()
                .map(blockMapper::toBlockDTO)
                .toList();

        log.info("Successfully fetched {} blocks", blocks.size());
        return blocks;
    }

    @Override
    public List<RoomResponseDTO> getBlockRooms(Long id) {
        log.info("Fetching rooms for block with ID: {}", id);

        Block block = blockRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Block with ID {} not found", id);
                    return new IllegalStateException("Block not found");
                });

        List<Room> rooms = block.getRooms();
        log.info("Successfully fetched {} rooms for block with ID: {}", rooms.size(), id);

        return rooms.stream()
                .map(roomMapper::toRoomDTO)
                .toList();
    }
}
