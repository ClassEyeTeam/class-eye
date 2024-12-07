package com.classeye.classservice.mapper;

import com.classeye.classservice.dto.BlockDTO;
import com.classeye.classservice.dto.request.BlockCreateDTO;
import com.classeye.classservice.dto.response.BlockResponseDTO;
import com.classeye.classservice.entity.Block;
import org.mapstruct.Mapper;

/**
 * @author Najat
 */
@Mapper(componentModel = "spring")
public interface BlockMapper {
    BlockResponseDTO toBlockDTO(Block block);
    Block toBlock(BlockCreateDTO blockCreateDTO);
}
