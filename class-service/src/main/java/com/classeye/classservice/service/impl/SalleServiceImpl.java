package com.classeye.classservice.service.impl;

import com.classeye.classservice.dto.SalleDTO;
import com.classeye.classservice.entity.Salle;
import com.classeye.classservice.mapper.SalleMapper;
import com.classeye.classservice.repository.SalleRepository;
import com.classeye.classservice.service.SalleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Najat
 */
@Service
@Slf4j
@AllArgsConstructor
public class SalleServiceImpl implements SalleService {

    private final SalleRepository salleRepository;
    private final SalleMapper salleMapper;


    @Override
    public SalleDTO createSalle(SalleDTO salleDTO) {
        log.info("Creating salle: {}", salleDTO);

        salleRepository.findByName(salleDTO.name())
                .ifPresent(salle -> {
                    throw new IllegalStateException("Salle already exists");
                });

        Salle salle = salleMapper.toSalle(salleDTO);
        Salle savedSalle = salleRepository.save(salle);
        log.info("Salle created: {}", savedSalle);
        return salleMapper.toSalleDTO(savedSalle);
    }

    @Override
    public SalleDTO updateSalle(SalleDTO salleDTO, Long id) {
        Salle salle = salleRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Salle not found"));
        salle.setName(salleDTO.name());
        salle.setCapacity(salleDTO.capacity());
        salle.setSalleType(salleDTO.salleType());
        //salle.setBlock(salleDTO.block());
        Salle updatedSalle = salleRepository.save(salle);
        return salleMapper.toSalleDTO(updatedSalle);
    }

    @Override
    public SalleDTO getSalle(Long id) {
        Salle salle = salleRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Salle not found"));
        return salleMapper.toSalleDTO(salle);

    }

    @Override
    public void deleteSalle(Long id) {
       salleRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Salle not found"));
        salleRepository.deleteById(id);
    }

    @Override
    public
    List<SalleDTO> getAllSalles() {
        return salleRepository.findAll().stream()
                .map(salleMapper::toSalleDTO)
                .toList();
    }
}
