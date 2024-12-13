package com.classeye.studentservice.dto;

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
    Long roomId,
    List<Long> moduleOptionIds

){

}
