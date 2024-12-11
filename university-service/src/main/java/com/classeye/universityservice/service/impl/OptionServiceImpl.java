package com.classeye.universityservice.service.impl;

import com.classeye.universityservice.dto.DepartmentDto;
import com.classeye.universityservice.dto.OptionDTO;
import com.classeye.universityservice.dto.response.OptionResponseDto;
import com.classeye.universityservice.entity.Department;
import com.classeye.universityservice.entity.Option;
import com.classeye.universityservice.exception.DuplicateResourceException;
import com.classeye.universityservice.mapper.OptionMapper;
import com.classeye.universityservice.repository.OptionRepository;
import com.classeye.universityservice.service.DepartmentService;
import com.classeye.universityservice.service.OptionService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author moham
 **/
@Service
@Slf4j
@AllArgsConstructor
public class OptionServiceImpl implements OptionService {

    private final OptionRepository optionRepository;
    private final DepartmentService departmentService;
    private final OptionMapper optionMapper;

    @Override
    public OptionResponseDto createOption(OptionDTO optionDTO) {
        log.info("Creating Option with name: {}", optionDTO.name());
        if (optionRepository.getOptionByName(optionDTO.name()).isPresent()) {
            log.error("Option with name '{}' already exists", optionDTO.name());
            throw new DuplicateResourceException("Option already exists with name: " + optionDTO.name());
        }
        DepartmentDto departmentDto = departmentService.getDepartmentDtoById(optionDTO.departmentId());
        Option option = optionMapper.toEntity(optionDTO);
        Option savedOption = optionRepository.save(option);

        log.info("Option created with ID: {}", savedOption.getId());
        return new OptionResponseDto(
                savedOption.getId(),
                savedOption.getName(),
                savedOption.getDescription(),
                departmentDto
        );
    }

    @Override
    @Transactional
    public OptionResponseDto updateOption(Long id, OptionDTO optionDTO) {
        log.info("Updating Option with ID: {}", id);

        // Fetch the existing Option
        Option existingOption = getOptionById(id);

        // Update department
        if (optionDTO.departmentId() != null) {
            log.info("Updating Department for Option with ID: {}", id);
            Department department = departmentService.getDepartmentById(optionDTO.departmentId());
            existingOption.setDepartment(department);
        }

        // Update basic fields
        existingOption.setName(optionDTO.name());
        existingOption.setDescription(optionDTO.description());
        // Save the updated Option entity
        Option savedOption = optionRepository.save(existingOption);
        log.info("Option updated with ID: {}", savedOption.getId());
        return optionMapper.toDto(savedOption);
    }

    @Override
    public void deleteOption(Long id) {
        log.info("Deleting Option with ID: {}", id);
        if (!optionRepository.existsById(id)) {
            log.error("Option with ID '{}' not found", id);
            throw new EntityNotFoundException("Option not found with id: " + id);
        }
        optionRepository.deleteById(id);
        log.info("Option deleted with ID: {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public OptionResponseDto getOptionDtoById(Long id) {
        log.info("Fetching Option DTO with ID: {}", id);
        Option option = optionRepository.findById(id).orElseThrow(() -> {
            log.error("Option with ID '{}' not found", id);
            return new EntityNotFoundException("Option not found with id: " + id);
        });

        return optionMapper.toDto(option);
    }

    @Override
    public Option getOptionById(Long id) {
        log.info("Fetching Option with ID: {}", id);
        return optionRepository.findById(id).orElseThrow(() -> {
            log.error("Option with ID '{}' not found", id);
            return new EntityNotFoundException("Option not found with id: " + id);
        });
    }

    @Override
    public List<OptionResponseDto> getAllOptions() {
        log.info("Fetching all Options");
        return optionRepository.findAll().stream()
                .map(optionMapper::toDto)
                .collect(Collectors.toList());
    }

//    @Override
//    public List<OptionDTO> getOptionsByDepartment(Long id) {
//        log.info("Fetching all Options for Department with ID: {}", id);
//        return optionRepository.getOptionsByDepartmentId(id).orElseThrow(() -> {
//                    log.error("No Options found for Department with ID: {}", id);
//                    return new EntityNotFoundException("No Options found for Department with ID: " + id);
//                }).stream()
//                .map(optionMapper::toDto)
//                .collect(Collectors.toList());
//
//    }

}

