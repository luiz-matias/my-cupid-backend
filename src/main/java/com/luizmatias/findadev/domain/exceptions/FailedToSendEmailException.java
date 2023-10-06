package com.luizmatias.findadev.domain.exceptions;

/**
 * Thrown when email has been failed to send
 */
public class FailedToSendEmailException extends Exception {

    public FailedToSendEmailException(String message) {
        super(message);
    }
}
