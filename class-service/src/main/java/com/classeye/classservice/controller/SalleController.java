package com.classeye.classservice.controller;

import com.classeye.classservice.dto.SalleDTO;
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
    public ResponseEntity<SalleDTO> createSalle(@Valid @RequestBody SalleDTO salleDTO) {
        SalleDTO createdSalle = salleService.createSalle(salleDTO);
        return new ResponseEntity<>(createdSalle, HttpStatus.CREATED);
    }

    // Update an existing salle
    @PutMapping("/{id}")
    public ResponseEntity<SalleDTO> updateSalle(@PathVariable long id, @RequestBody @Valid SalleDTO salleDTO) {
        SalleDTO updatedSalle = salleService.updateSalle(salleDTO, id);
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
    public ResponseEntity<SalleDTO> getSalleById(@PathVariable long id) {
        SalleDTO salleDTO = salleService.getSalle(id);
        return ResponseEntity.ok(salleDTO);
    }

    // Get a list of all salles
    @GetMapping
    public ResponseEntity<List<SalleDTO>> getAllSalles() {
        List<SalleDTO> salles = salleService.getAllSalles();
        return ResponseEntity.ok(salles);
    }

}
