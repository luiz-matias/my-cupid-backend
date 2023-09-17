package com.luizmatias.findadev.domain.exceptions;

/**
 * Thrown when a user tries to like another with the same user type
 */
public class LikeOnSameUserTypeException extends Exception {

    public LikeOnSameUserTypeException(String message) {
        super(message);
    }
}
