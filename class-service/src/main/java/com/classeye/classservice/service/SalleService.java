package com.classeye.classservice.service;

import com.classeye.classservice.dto.request.SalleCreateDTO;
import com.classeye.classservice.dto.response.SalleResponseDTO;

import java.util.List;

/**
 * @author Najat
 */
public interface SalleService {
    SalleResponseDTO createSalle(SalleCreateDTO salleCreateDTO);
    SalleResponseDTO updateSalle(SalleCreateDTO salleCreateDTO, Long id);
    SalleResponseDTO getSalle(Long id);
    void deleteSalle(Long id);
    List<SalleResponseDTO> getAllSalles();
}
