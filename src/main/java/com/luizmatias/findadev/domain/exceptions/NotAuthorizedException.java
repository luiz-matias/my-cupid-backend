package com.luizmatias.findadev.domain.exceptions;

/**
 * Thrown when user doesn't have access to a certain feature
 */
public class NotAuthorizedException extends Exception {

    public NotAuthorizedException(String message) {
        super(message);
    }
}
