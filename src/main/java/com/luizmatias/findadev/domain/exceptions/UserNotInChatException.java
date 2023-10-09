package com.luizmatias.findadev.domain.exceptions;

/**
 * Thrown when user isn't part of the given chat
 */
public class UserNotInChatException extends Exception {

    public UserNotInChatException(String message) {
        super(message);
    }
}
