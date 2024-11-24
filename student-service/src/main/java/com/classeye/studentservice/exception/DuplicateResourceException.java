package com.classeye.studentservice.exception;

/**
 * @author sejja
 **/

public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }
}