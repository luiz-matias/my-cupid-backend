package com.luizmatias.mycupid.domain.exceptions;

/**
 * Thrown when a user wasn't verified yet
 */
public class InactiveUserException extends Exception {

    public InactiveUserException(String message) {
        super(message);
    }
}
