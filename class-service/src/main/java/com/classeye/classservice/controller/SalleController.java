package com.classeye.classservice.controller;


import com.classeye.classservice.dto.request.SalleCreateDTO;
import com.classeye.classservice.dto.response.SalleResponseDTO;
import com.classeye.classservice.service.SalleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Najat
 */
@RestController
@RequestMapping("/salles")
@AllArgsConstructor
public class SalleController {
    private final SalleService salleService;

    // Create a new salle
    @PostMapping
    public ResponseEntity<SalleResponseDTO> createSalle(@Valid @RequestBody SalleCreateDTO salleCreateDTO) {
        SalleResponseDTO createdSalle = salleService.createSalle(salleCreateDTO);
        return new ResponseEntity<>(createdSalle, HttpStatus.CREATED);
    }

    // Update an existing salle
    @PutMapping("/{id}")
    public ResponseEntity<SalleResponseDTO> updateSalle(@PathVariable long id, @RequestBody @Valid SalleCreateDTO salleCreateDTO) {
        SalleResponseDTO updatedSalle = salleService.updateSalle(salleCreateDTO, id);
        return ResponseEntity.ok(updatedSalle);
    }

    // Delete a salle by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalle(@PathVariable long id) {
        salleService.deleteSalle(id);
        return ResponseEntity.noContent().build();
    }

    // Get a salle by ID
    @GetMapping("/{id}")
    public ResponseEntity<SalleResponseDTO> getSalleById(@PathVariable long id) {
        SalleResponseDTO salleDTO = salleService.getSalle(id);
        return ResponseEntity.ok(salleDTO);
    }

    // Get a list of all salles
    @GetMapping
    public ResponseEntity<List<SalleResponseDTO>> getAllSalles() {
        List<SalleResponseDTO> salles = salleService.getAllSalles();
        return ResponseEntity.ok(salles);
    }

}
