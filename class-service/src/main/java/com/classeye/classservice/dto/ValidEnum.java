package com.classeye.classservice.dto;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author moham
 **/
@Constraint(validatedBy = EnumValidator.class)
@Target({FIELD, PARAMETER})
@Retention(RUNTIME)
public @interface ValidEnum {
    String message() default "Invalid value. Allowed values are: {allowedValues}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<? extends Enum<?>> enumClass();

    boolean ignoreCase() default false;
}
