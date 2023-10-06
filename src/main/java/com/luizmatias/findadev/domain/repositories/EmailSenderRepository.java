package com.luizmatias.findadev.domain.repositories;

import com.luizmatias.findadev.domain.exceptions.FailedToSendEmailException;

public interface EmailSenderRepository {

    void sendEmail(String from, String to, String subject, String body) throws FailedToSendEmailException;
    void sendPasswordRecoveryEmail(String token, String to) throws FailedToSendEmailException;

}
