package com.classeye.studentservice.mapper;

/**
 * @author moham
 **/
import com.classeye.studentservice.dto.request.SessionRequestDTO;
import com.classeye.studentservice.dto.response.SessionResponseDTO;
import com.classeye.studentservice.entity.Session;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SessionMapper {

    SessionMapper INSTANCE = Mappers.getMapper(SessionMapper.class);
    Session toEntity(SessionRequestDTO sessionRequestDTO);
    SessionResponseDTO toDto(Session session);
}

