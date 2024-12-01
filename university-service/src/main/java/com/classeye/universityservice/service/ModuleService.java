package com.classeye.universityservice.service;

import com.classeye.universityservice.dto.ModuleDTO;
import com.classeye.universityservice.entity.Module;

import java.util.List;

/**
 * @author sejja
 **/
public interface ModuleService {
    ModuleDTO createModule(ModuleDTO moduleDTO);
    ModuleDTO updateModule(Long id, ModuleDTO moduleDTO);
    void deleteModule(Long id);
    ModuleDTO getModuleDtoById(Long id);
    Module getModuleById(Long id);

    List<ModuleDTO> getAllModules();
}
