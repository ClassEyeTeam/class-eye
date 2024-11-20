package com.classeye.universityservice.service;


import com.classeye.universityservice.dto.request.ModuleOptionRequestDTO;
import com.classeye.universityservice.dto.response.ModuleOptionResponseDTO;
import com.classeye.universityservice.entity.ModuleOption;


import java.util.List;

/**
 * @author sejja
 **/
public interface  ModuleOptionService {
    ModuleOptionResponseDTO createModuleOption(ModuleOptionRequestDTO moduleOptionDTO);
    ModuleOptionResponseDTO updateModuleOption(Long id, ModuleOptionRequestDTO moduleOptionDTO);
    void deleteModuleOption(Long id);
    ModuleOptionResponseDTO getModuleOptionDtoById(Long id);
    ModuleOption getModuleOptionById(Long id);
    List<ModuleOptionResponseDTO> getAllModuleOptions();

    List<ModuleOption> findAllById(List<Long> ids);
}
