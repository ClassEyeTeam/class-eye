package com.classeye.universityservice.exception;

/**
 * @author sejja
 **/

public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }
}