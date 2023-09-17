package com.luizmatias.findadev.domain.exceptions;

/**
 * Thrown when a user tries to like himself
 */
public class LikeOnSameUserException extends Exception {

    public LikeOnSameUserException(String message) {
        super(message);
    }
}
