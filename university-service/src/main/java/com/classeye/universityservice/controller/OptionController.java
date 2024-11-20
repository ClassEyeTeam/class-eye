package com.classeye.universityservice.controller;

import com.classeye.universityservice.dto.OptionDTO;
import com.classeye.universityservice.dto.response.OptionModulesDTO;
import com.classeye.universityservice.service.OptionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author sejja
 **/

@RestController
@RequestMapping("/options")
@RequiredArgsConstructor
public class OptionController {

    private final OptionService optionService;

    @PostMapping
    public ResponseEntity<OptionDTO> createOption(@RequestBody @Valid OptionDTO optionDTO) {
        return ResponseEntity.ok(optionService.createOption(optionDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OptionDTO> updateOption(@PathVariable Long id, @RequestBody @Valid OptionDTO optionDTO) {
        return ResponseEntity.ok(optionService.updateOption(id, optionDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOption(@PathVariable Long id) {
        optionService.deleteOption(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OptionModulesDTO> getOptionById(@PathVariable Long id) {
        return ResponseEntity.ok(optionService.getOptionDtoById(id));
    }

    @GetMapping
    public ResponseEntity<List<OptionDTO>> getAllOptions() {
        return ResponseEntity.ok(optionService.getAllOptions());
    }

    // Add a new endpoint to get all options for a given department
    @GetMapping("/department/{id}")
    public ResponseEntity<List<OptionDTO>> getOptionsByDepartment(@PathVariable Long id) {
        return ResponseEntity.ok(optionService.getOptionsByDepartment(id));
    }
}
