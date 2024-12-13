package com.classeye.universityservice.controller;

import com.classeye.universityservice.dto.request.ModuleOptionRequestDTO;
import com.classeye.universityservice.dto.response.ModuleOptionResponseDTO;
import com.classeye.universityservice.service.ModuleOptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author sejja
 **/

@RestController
@RequestMapping("/module-options")
@RequiredArgsConstructor
public class ModuleOptionController {

    private final ModuleOptionService moduleOptionService;

    @PostMapping
    public ResponseEntity<ModuleOptionResponseDTO> createModuleOption(@RequestBody @Valid ModuleOptionRequestDTO moduleOptionRequestDTO) {
        return ResponseEntity.ok(moduleOptionService.createModuleOption(moduleOptionRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModuleOptionResponseDTO> updateModuleOption(@PathVariable Long id, @RequestBody @Valid ModuleOptionRequestDTO moduleOptionRequestDTO) {
        return ResponseEntity.ok(moduleOptionService.updateModuleOption(id, moduleOptionRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModuleOption(@PathVariable Long id) {
        moduleOptionService.deleteModuleOption(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModuleOptionResponseDTO> getModuleOptionById(@PathVariable Long id) {
        return ResponseEntity.ok(moduleOptionService.getModuleOptionDtoById(id));
    }

    @GetMapping("/option/{id}")
    public ResponseEntity<List<ModuleOptionResponseDTO>> getAllModulesInOption(@PathVariable Long id) {
        return ResponseEntity.ok(moduleOptionService.getAllModulesInOption(id));
    }

    @GetMapping
    public ResponseEntity<List<ModuleOptionResponseDTO>> getAllModuleOptions() {
        return ResponseEntity.ok(moduleOptionService.getAllModuleOptions());
    }
}
