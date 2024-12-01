package com.classeye.classservice.service;

import com.classeye.classservice.dto.BlockDTO;
import com.classeye.classservice.entity.Block;

import java.util.List;

/**
 * @author Najat
 */
public interface BlockService {
    BlockDTO createBlock(BlockDTO blockDTO);
    BlockDTO updateBlock(BlockDTO blockDTO, Long id);
    void deleteBlock(Long id);
    BlockDTO getBlock(Long id);
    List<BlockDTO> getAllBlocks();
}
