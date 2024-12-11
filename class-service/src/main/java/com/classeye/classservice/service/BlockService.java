package com.classeye.classservice.service;

import com.classeye.classservice.dto.request.BlockRequestDTO;
import com.classeye.classservice.dto.response.BlockResponseDTO;
import com.classeye.classservice.dto.response.RoomResponseDTO;
import com.classeye.classservice.entity.Block;

import java.util.List;

/**
 * @author Najat
 */
public interface BlockService {
    BlockResponseDTO createBlock(BlockRequestDTO blockRequestDTO);
    BlockResponseDTO updateBlock(BlockRequestDTO blockRequestDTO, Long id);
    void deleteBlock(Long id);
    BlockResponseDTO getBlockDtoById(Long id);
    Block getBlockById(Long id);
    List<BlockResponseDTO> getAllBlocks();

    List<RoomResponseDTO> getBlockRooms(Long id);
}
