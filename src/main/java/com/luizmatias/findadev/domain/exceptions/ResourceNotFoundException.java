package com.luizmatias.findadev.domain.exceptions;

/**
 * Thrown when an entity could not be found
 */
public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
