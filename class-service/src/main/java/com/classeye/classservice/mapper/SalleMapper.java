package com.classeye.classservice.mapper;



import com.classeye.classservice.dto.request.SalleCreateDTO;
import com.classeye.classservice.dto.response.SalleResponseDTO;
import com.classeye.classservice.entity.Salle;
import org.mapstruct.Mapper;

/**
 * @author Najat
 */
@Mapper(componentModel = "spring")
public interface SalleMapper {
    SalleResponseDTO toSalleDTO(Salle salle);
    Salle toSalle(SalleCreateDTO salleCreateDTO);
}
