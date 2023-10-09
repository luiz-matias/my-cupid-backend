package com.luizmatias.findadev.domain.repositories;

import com.luizmatias.findadev.domain.exceptions.FailedToSendEmailException;

public interface EmailSenderRepository {

    void sendEmail(String from, String to, String subject, String body) throws FailedToSendEmailException;

    void sendVerifyAccountEmail(String token, String to, String username) throws FailedToSendEmailException;

    void sendPasswordRecoveryEmail(String token, String to, String username) throws FailedToSendEmailException;

    void sendPasswordChangedEmail(String to, String username) throws FailedToSendEmailException;

}
