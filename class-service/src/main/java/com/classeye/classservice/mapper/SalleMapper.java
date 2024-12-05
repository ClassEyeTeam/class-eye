package com.classeye.classservice.mapper;

import com.classeye.classservice.dto.SalleDTO;
import com.classeye.classservice.entity.Salle;
import org.mapstruct.Mapper;

/**
 * @author Najat
 */
@Mapper(componentModel = "spring")
public interface SalleMapper {
    SalleDTO toSalleDTO(Salle salle);
    Salle toSalle(SalleDTO salleDTO);
}
