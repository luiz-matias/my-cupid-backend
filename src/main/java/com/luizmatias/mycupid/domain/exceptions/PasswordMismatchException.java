package com.luizmatias.mycupid.domain.exceptions;

/**
 * Thrown when user passwords doesn't match
 */
public class PasswordMismatchException extends Exception {

    public PasswordMismatchException(String message) {
        super(message);
    }
}
