package com.luizmatias.findadev.domain.exceptions;

/**
 * Thrown when user login could not be found
 */
public class LoginFailedException extends Exception {

    public LoginFailedException(String message) {
        super(message);
    }
}
