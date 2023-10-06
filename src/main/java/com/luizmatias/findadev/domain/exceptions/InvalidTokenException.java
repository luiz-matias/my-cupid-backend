package com.luizmatias.findadev.domain.exceptions;

/**
 * Thrown when an token is invalid
 */
public class InvalidTokenException extends Exception {

    public InvalidTokenException(String message) {
        super(message);
    }
}
