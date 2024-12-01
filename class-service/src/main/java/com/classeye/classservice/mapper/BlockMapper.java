package com.classeye.classservice.mapper;

import com.classeye.classservice.dto.BlockDTO;
import com.classeye.classservice.entity.Block;
import org.mapstruct.Mapper;

/**
 * @author Najat
 */
@Mapper(componentModel = "spring")
public interface BlockMapper {
    BlockDTO toBlockDTO(Block block);
    Block toBlock(BlockDTO blockDTO);
}
