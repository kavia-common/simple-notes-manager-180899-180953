package com.example.notesappbackend.exception;

/**
 * Thrown when a requested resource cannot be found.
 */
// PUBLIC_INTERFACE
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
