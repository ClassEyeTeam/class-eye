package com.classeye.classservice.service;

import com.classeye.classservice.dto.BlockDTO;
import com.classeye.classservice.dto.SalleDTO;
import com.classeye.classservice.dto.request.BlockCreateDTO;
import com.classeye.classservice.dto.response.BlockResponseDTO;
import com.classeye.classservice.dto.response.SalleResponseDTO;

import java.util.List;

/**
 * @author Najat
 */
public interface BlockService {
    BlockResponseDTO createBlock(BlockCreateDTO blockCreateDTO);
    BlockResponseDTO updateBlock(BlockCreateDTO blockCreateDTO, Long id);
    void deleteBlock(Long id);
    BlockResponseDTO getBlock(Long id);
    List<BlockResponseDTO> getAllBlocks();

    // Getting all the salles in a block
    List<SalleResponseDTO> getBlockSalles(Long id);
}
