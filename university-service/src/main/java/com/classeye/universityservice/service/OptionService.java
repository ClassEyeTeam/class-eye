package com.classeye.universityservice.service;

import com.classeye.universityservice.dto.OptionDTO;
import com.classeye.universityservice.dto.response.OptionResponseDto;
import com.classeye.universityservice.entity.Option;

import java.util.List;

/**
 * @author sejja
 **/
public interface OptionService {
    OptionResponseDto createOption(OptionDTO optionDTO);
    OptionResponseDto updateOption(Long id, OptionDTO optionDTO);
    void deleteOption(Long id);
    OptionResponseDto getOptionDtoById(Long id);
    Option getOptionById(Long id);
    List<OptionResponseDto> getAllOptions();

//    List<OptionDTO> getOptionsByDepartment(Long id);
}
