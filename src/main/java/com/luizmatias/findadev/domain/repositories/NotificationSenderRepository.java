package com.luizmatias.findadev.domain.repositories;

import com.luizmatias.findadev.domain.entities.User;
import com.luizmatias.findadev.domain.exceptions.FailedToSendNotificationException;

public interface NotificationSenderRepository {

    void send(String from, String to, String subject, String body) throws FailedToSendNotificationException;

    void sendVerifyAccount(String token, String to, String username) throws FailedToSendNotificationException;

    void sendPasswordRecovery(String token, String to, String username) throws FailedToSendNotificationException;

    void sendChangePassword(String token, String to, String username) throws FailedToSendNotificationException;

    void sendPasswordChanged(String to, String username) throws FailedToSendNotificationException;

    void sendMatchNotification(String to, String username, User matchedUser) throws FailedToSendNotificationException;

}
