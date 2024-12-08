package com.classeye.classservice.mapper;

import com.classeye.classservice.dto.BlockDTO;
import com.classeye.classservice.dto.request.BlockCreateDTO;
import com.classeye.classservice.dto.response.BlockResponseDTO;
import com.classeye.classservice.entity.Block;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author Najat
 */
@Mapper(componentModel = "spring", uses = {SalleMapper.class})
public interface BlockMapper {
    // this is a way to create a mapper instance without using the new keyword and it is thread-safe
    BlockMapper INSTANCE = Mappers.getMapper(BlockMapper.class);

    @Mapping(target = "salles", source = "salles")
    BlockResponseDTO toBlockDTO(Block block);

    @Mapping(target = "id", ignore = true)
    Block toBlock(BlockCreateDTO blockCreateDTO);

//    @Mapping(target = "salles", source = "salles")
//    BlockSallesDTO toSallesDTO(Block block);

}
