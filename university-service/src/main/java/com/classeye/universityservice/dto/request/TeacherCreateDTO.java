package com.classeye.universityservice.dto.request;

/**
 * @author moham
 **/
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record TeacherCreateDTO(
        @NotEmpty(message = "Name cannot be empty")
        @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
        String name,

        @NotEmpty(message = "Email cannot be empty")
        @Email(message = "Email must be a valid email address")
        String email,

        @NotEmpty(message = "Phone number cannot be empty")
        @Pattern(
                regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$",
                message = "Phone number must be a valid 10-digit number, optionally prefixed by a country code"
        )
        String phone,

        @NotEmpty(message = "Address cannot be empty")
        @Size(max = 200, message = "Address cannot exceed 200 characters")
        String address,

        @Positive(message = "Department ID must be a positive number")
        long departmentId
) {}
