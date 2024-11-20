package com.classeye.universityservice.service;

import com.classeye.universityservice.dto.OptionDTO;
import com.classeye.universityservice.dto.response.OptionModulesDTO;
import com.classeye.universityservice.entity.Option;

import java.util.List;

/**
 * @author sejja
 **/
public interface OptionService {
    OptionDTO createOption(OptionDTO optionDTO);
    OptionDTO updateOption(Long id, OptionDTO optionDTO);
    void deleteOption(Long id);
    OptionModulesDTO getOptionDtoById(Long id);
    Option getOptionById(Long id);
    List<OptionDTO> getAllOptions();

    List<OptionDTO> getOptionsByDepartment(Long id);
}
