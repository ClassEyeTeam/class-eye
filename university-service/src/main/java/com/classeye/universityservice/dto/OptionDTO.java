package com.classeye.universityservice.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * @author sejja
 **/
public record OptionDTO (
    long id,
    @NotEmpty(message = "Name cannot be Empty")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    String name,
    String description,
    Long departmentId,
    List<Long> moduleOptionIds

){

    public OptionDTO(long id, String name, String description) {
        this(id, name, description, null, null);
    }
    public OptionDTO(String name, String description, Long departmentId) {
        this(0, name, description, departmentId, null);
    }
}
