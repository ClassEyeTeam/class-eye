package com.classeye.classservice.exception;

import com.classeye.universityservice.dto.ApiError;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

/**
 * Author: sejja
 **/

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiError> handleDuplicateResourceException(DuplicateResourceException ex, WebRequest request) {
        return createErrorResponse(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiError> handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        return createErrorResponse(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        String message = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining("; "));
        return createErrorResponse(
                message,
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    @ExceptionHandler({JpaSystemException.class, DataIntegrityViolationException.class, ConstraintViolationException.class})
    public ResponseEntity<ApiError> handleJpaExceptions(Exception ex, WebRequest request) {
        String message = "Database error: " + ex.getMessage();
        return createErrorResponse(
                message,
                HttpStatus.INTERNAL_SERVER_ERROR,
                request
        );
    }

    private ResponseEntity<ApiError> createErrorResponse(
            String message,
            HttpStatus status,
            WebRequest request) {
        ApiError apiError = new ApiError(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                ((ServletWebRequest) request).getRequest().getRequestURI()
        );
        return new ResponseEntity<>(apiError, status);
    }
}