package com.luizmatias.mycupid.domain.exceptions;

/**
 * Thrown when a notification has been failed to send
 */
public class FailedToSendNotificationException extends Exception {

    public FailedToSendNotificationException(String message) {
        super(message);
    }
}
