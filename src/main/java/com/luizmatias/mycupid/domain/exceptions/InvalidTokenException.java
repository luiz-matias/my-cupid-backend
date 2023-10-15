package com.luizmatias.mycupid.domain.exceptions;

/**
 * Thrown when a token is invalid
 */
public class InvalidTokenException extends Exception {

    public InvalidTokenException(String message) {
        super(message);
    }
}
