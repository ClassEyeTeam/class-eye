package com.classeye.universityservice.service.impl;

import com.classeye.universityservice.dto.ModuleDTO;
import com.classeye.universityservice.exception.DuplicateResourceException;
import com.classeye.universityservice.mapper.ModuleMapper;
import com.classeye.universityservice.repository.ModuleRepository;
import com.classeye.universityservice.service.ModuleService;
import com.classeye.universityservice.entity.Module;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@AllArgsConstructor
public class ModuleServiceImpl implements ModuleService {

    private final ModuleRepository moduleRepository;
    private final ModuleMapper moduleMapper;

    @Override
    public ModuleDTO createModule(ModuleDTO moduleDTO) {
        log.info("Creating module with name: {}", moduleDTO.name());
        if (moduleRepository.existsByName(moduleDTO.name())) {
            log.error("Module with name {} already exists", moduleDTO.name());
            throw new DuplicateResourceException("Module with name " + moduleDTO.name() + " already exists");
        }
        Module module = moduleMapper.toEntity(moduleDTO);
        Module savedModule = moduleRepository.save(module);
        log.info("Module created with ID: {}", savedModule.getId());
        return moduleMapper.toDto(savedModule);
    }

    @Override
    public ModuleDTO updateModule(Long id, ModuleDTO moduleDTO) {
        getModuleById(id);
        log.info("Updating module with ID: {}", id);
        Module existModule =getModuleById(id);
        existModule.setName(moduleDTO.name());
        existModule.setDescription(moduleDTO.description());

        Module updatedModule = moduleRepository.save(existModule);
        log.info("Module updated with ID: {}", updatedModule.getId());
        return moduleMapper.toDto(updatedModule);
    }

    @Override
    public void deleteModule(Long id) {
        log.info("Deleting module with ID: {}", id);
        moduleRepository.deleteById(id);
        log.info("Module deleted with ID: {}", id);
    }

    @Override
    public ModuleDTO getModuleDtoById(Long id) {
        log.info("Fetching module with ID: {}", id);
        Module module = moduleRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Module not found with id: " + id));
        if (module == null) {
            log.warn("Module with ID {} not found", id);
        }
        return moduleMapper.toDto(module);
    }

    @Override
    public Module getModuleById(Long id) {
        log.info("Fetching module with ID: {}", id);
        Module module = moduleRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Module not found with id: " + id));
        if (module == null) {
            log.warn("Module with ID {} not found", id);
        }
        return module;
    }

    @Override
    public List<ModuleDTO> getAllModules() {
        log.info("Fetching all modules");
        return moduleRepository.findAll().stream()
                .map(moduleMapper::toDto)
                .collect(Collectors.toList());
    }
}