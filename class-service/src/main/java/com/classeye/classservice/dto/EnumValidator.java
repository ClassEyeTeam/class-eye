package com.classeye.classservice.dto;

/**
 * @author moham
 **/

import com.classeye.classservice.entity.RoomType;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class EnumValidator implements ConstraintValidator<ValidEnum, RoomType> {

    private Class<? extends Enum<?>> enumClass;
    private boolean ignoreCase;

    @Override
    public void initialize(ValidEnum annotation) {
        this.enumClass = annotation.enumClass();
        this.ignoreCase = annotation.ignoreCase();
    }

    @Override
    public boolean isValid(RoomType value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Let @NotNull handle null checks
        }

        return Arrays.stream(enumClass.getEnumConstants())
                .anyMatch(enumConstant -> enumConstant.name().equals(value.name()));
    }
}


