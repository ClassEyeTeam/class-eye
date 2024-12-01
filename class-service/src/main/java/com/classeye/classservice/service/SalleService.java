package com.classeye.classservice.service;

import com.classeye.classservice.dto.SalleDTO;

import java.util.List;

/**
 * @author Najat
 */
public interface SalleService {
    SalleDTO createSalle(SalleDTO salleDTO);
    SalleDTO updateSalle(SalleDTO salleDTO, Long id);
    SalleDTO getSalle(Long id);
    void deleteSalle(Long id);
    List<SalleDTO> getAllSalles();
}
