package com.classeye.universityservice.service.impl;

import com.classeye.universityservice.dto.request.ModuleOptionRequestDTO;
import com.classeye.universityservice.dto.response.ModuleOptionResponseDTO;
import com.classeye.universityservice.entity.ModuleOption;
import com.classeye.universityservice.entity.Module;
import com.classeye.universityservice.entity.Option;
import com.classeye.universityservice.entity.Teacher;
import com.classeye.universityservice.mapper.ModuleOptionMapper;
import com.classeye.universityservice.repository.ModuleOptionRepository;
import com.classeye.universityservice.service.ModuleOptionService;
import com.classeye.universityservice.service.ModuleService;
import com.classeye.universityservice.service.OptionService;
import com.classeye.universityservice.service.TeacherService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sejja
 **/
@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class ModuleOptionServiceImpl implements ModuleOptionService {

    private final ModuleOptionRepository moduleOptionRepository;
    private final ModuleOptionMapper moduleOptionMapper;
    private final TeacherService teacherService;
    private final ModuleService moduleService;
    private final OptionService optionService;

    @Override
    public ModuleOptionResponseDTO createModuleOption(ModuleOptionRequestDTO moduleOptionRequestDTO) {
        log.info("Checking ModuleOption for Module ID: {}, Teacher ID: {}, Option ID: {}",
                moduleOptionRequestDTO.moduleId(),
                moduleOptionRequestDTO.teacherId(),
                moduleOptionRequestDTO.optionId());

        // Validate related entities
        Module module = moduleService.getModuleById(moduleOptionRequestDTO.moduleId());
        Teacher teacher = teacherService.getTeacherById(moduleOptionRequestDTO.teacherId());
        Option option = optionService.getOptionById(moduleOptionRequestDTO.optionId());

        // Save entity
        ModuleOption moduleOption = moduleOptionMapper.toEntity(moduleOptionRequestDTO);
        moduleOption.setModule(module);
        moduleOption.setOption(option);
        moduleOption.setTeacher(teacher);
        ModuleOption savedModuleOption = moduleOptionRepository.save(moduleOption);

        log.info("ModuleOption created with ID: {}", savedModuleOption.getId());
        return moduleOptionMapper.toDto(savedModuleOption);
    }

    @Override
    public ModuleOptionResponseDTO updateModuleOption(Long id, ModuleOptionRequestDTO moduleOptionRequestDTO) {
        log.info("Updating ModuleOption with ID: {}", id);

        // Validate existence
        ModuleOption existingModuleOption = moduleOptionRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("ModuleOption with ID {} not found", id);
                    return new EntityNotFoundException("ModuleOption not found with id: " + id);
                });

        // Map data
        existingModuleOption.setModule(moduleService.getModuleById(moduleOptionRequestDTO.moduleId()));
        existingModuleOption.setTeacher(teacherService.getTeacherById(moduleOptionRequestDTO.teacherId()));
        existingModuleOption.setOption(optionService.getOptionById(moduleOptionRequestDTO.optionId()));

        // Update entity
        ModuleOption savedModuleOption = moduleOptionRepository.save(existingModuleOption);

        log.info("ModuleOption updated with ID: {}", savedModuleOption.getId());
        return moduleOptionMapper.toDto(savedModuleOption);
    }

    @Override
    public void deleteModuleOption(Long id) {
        log.info("Deleting ModuleOption with ID: {}", id);

        if (!moduleOptionRepository.existsById(id)) {
            log.error("ModuleOption with ID {} not found", id);
            throw new EntityNotFoundException("ModuleOption not found with id: " + id);
        }

        moduleOptionRepository.deleteById(id);
        log.info("ModuleOption deleted with ID: {}", id);
    }

    @Override
    public ModuleOptionResponseDTO getModuleOptionDtoById(Long id) {
        log.info("Fetching ModuleOption DTO with ID: {}", id);

        ModuleOption moduleOption = moduleOptionRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("ModuleOption with ID {} not found", id);
                    return new EntityNotFoundException("ModuleOption not found with id: " + id);
                });

        return moduleOptionMapper.toDto(moduleOption);
    }

    @Override
    public ModuleOption getModuleOptionById(Long id) {
        log.info("Fetching ModuleOption with ID: {}", id);

        return moduleOptionRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("ModuleOption with ID {} not found", id);
                    return new EntityNotFoundException("ModuleOption not found with id: " + id);
                });
    }

    @Override
    public List<ModuleOptionResponseDTO> getAllModuleOptions() {
        log.info("Fetching all ModuleOptions");

        return moduleOptionRepository.findAll().stream()
                .map(moduleOptionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<ModuleOptionResponseDTO> getAllModulesInOption(Long optionId) {
        log.info("Fetching all ModuleOptions by Option ID: {}", optionId);
        return moduleOptionRepository
                .getModuleOptionByOptionId(optionId)
                .stream()
                .map(moduleOptionMapper::toDto)
                .collect(Collectors.toList());
    }


    @Override
    public List<ModuleOption> findAllById(List<Long> ids) {
        log.info("Fetching all ModuleOptions by IDs: {}", ids);

        return moduleOptionRepository.findAllById(ids);
    }
}

