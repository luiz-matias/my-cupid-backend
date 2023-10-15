package com.luizmatias.mycupid.domain.exceptions;

/**
 * Thrown when an entity already exists
 */
public class ResourceAlreadyExistsException extends Exception {

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }
}
