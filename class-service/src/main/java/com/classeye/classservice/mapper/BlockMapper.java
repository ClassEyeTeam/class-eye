package com.classeye.classservice.mapper;


import com.classeye.classservice.dto.request.BlockRequestDTO;
import com.classeye.classservice.dto.response.BlockResponseDTO;
import com.classeye.classservice.entity.Block;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author Najat
 */
@Mapper(componentModel = "spring", uses = {RoomMapper.class})
public interface BlockMapper {
    BlockMapper INSTANCE = Mappers.getMapper(BlockMapper.class);

    BlockResponseDTO toBlockDTO(Block block);

    @Mapping(target = "id", ignore = true)
    Block toBlock(BlockRequestDTO blockRequestDTO);


}
