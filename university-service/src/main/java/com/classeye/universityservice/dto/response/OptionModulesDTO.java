package com.classeye.universityservice.dto.response;

import com.classeye.universityservice.dto.ModuleDTO;

import java.util.List;

/**
 * @author sejja
 **/
public record OptionModulesDTO(
        Long id,
        String name,
        String description,
        List<ModuleDTO> modules


) {
}
