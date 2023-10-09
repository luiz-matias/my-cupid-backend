package com.luizmatias.findadev.email;

import com.luizmatias.findadev.domain.exceptions.FailedToSendEmailException;
import com.luizmatias.findadev.domain.repositories.EmailSenderRepository;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.springframework.beans.factory.annotation.Value;

public class SendGridEmailSender implements EmailSenderRepository {

    @Value("${sendgrid.apikey}")
    private String apiKey;
    @Value("${sendgrid.from.email}")
    private String applicationEmail;
    private static final String verifyAccountTemplateId = "d-30588cbd007246feacb447281d159075";
    private static final String recoverPasswordTemplateId = "d-9ea0cdfbe03c4eea984375ff615954f6";
    private static final String passwordChangedTemplateId = "d-8ef01364fdbc418e8cd9279056ab0929";
    private static final String changePasswordTemplateId = "d-f2bd8f320c3c434ba538970e62881800";

    @Override
    public void sendEmail(String from, String to, String subject, String body) throws FailedToSendEmailException {
        Email mailFrom = new Email(from);
        Email mailTo = new Email(to);
        Content content = new Content("text/plain", body);
        Mail mail = new Mail(mailFrom, subject, mailTo, content);

        SendGrid sg = new SendGrid(apiKey);

        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            if (response.getStatusCode() != 202) {
                throw new FailedToSendEmailException("failed to send email to recover password");
            }
        } catch (Exception e) {
            throw new FailedToSendEmailException("failed to send email to recover password");
        }
    }

    @Override
    public void sendVerifyAccountEmail(String token, String to, String username) throws FailedToSendEmailException {
        Email mailFrom = new Email(applicationEmail);
        Email mailTo = new Email(to);
        Mail mail = new Mail();

        mail.setFrom(mailFrom);
        mail.setTemplateId(verifyAccountTemplateId);

        Personalization personalization = new Personalization();
        personalization.addTo(mailTo);
        personalization.addDynamicTemplateData("token", token);
        personalization.addDynamicTemplateData("username", username);

        mail.addPersonalization(personalization);

        SendGrid sg = new SendGrid(apiKey);

        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            if (response.getStatusCode() != 202) {
                throw new FailedToSendEmailException("failed to send email to verify account");
            }
        } catch (Exception e) {
            throw new FailedToSendEmailException("failed to send email to verify account");
        }
    }

    @Override
    public void sendPasswordChangedEmail(String to, String username) throws FailedToSendEmailException {
        Email mailFrom = new Email(applicationEmail);
        Email mailTo = new Email(to);
        Mail mail = new Mail();

        mail.setFrom(mailFrom);
        mail.setTemplateId(passwordChangedTemplateId);

        Personalization personalization = new Personalization();
        personalization.addTo(mailTo);
        personalization.addDynamicTemplateData("username", username);

        mail.addPersonalization(personalization);

        SendGrid sg = new SendGrid(apiKey);

        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            if (response.getStatusCode() != 202) {
                throw new FailedToSendEmailException("failed to send email to inform password changed");
            }
        } catch (Exception e) {
            throw new FailedToSendEmailException("failed to send email to inform password changed");
        }
    }

    @Override
    public void sendChangePasswordEmail(String token, String to, String username) throws FailedToSendEmailException {
        Email mailFrom = new Email(applicationEmail);
        Email mailTo = new Email(to);
        Mail mail = new Mail();

        mail.setFrom(mailFrom);
        mail.setTemplateId(changePasswordTemplateId);

        Personalization personalization = new Personalization();
        personalization.addTo(mailTo);
        personalization.addDynamicTemplateData("token", token);
        personalization.addDynamicTemplateData("username", username);

        mail.addPersonalization(personalization);

        SendGrid sg = new SendGrid(apiKey);

        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            if (response.getStatusCode() != 202) {
                throw new FailedToSendEmailException("failed to send email to confirm password change");
            }
        } catch (Exception e) {
            throw new FailedToSendEmailException("failed to send email to confirm password change");
        }
    }

    @Override
    public void sendPasswordRecoveryEmail(String token, String to, String username) throws FailedToSendEmailException {
        Email mailFrom = new Email(applicationEmail);
        Email mailTo = new Email(to);
        Mail mail = new Mail();

        mail.setFrom(mailFrom);
        mail.setTemplateId(recoverPasswordTemplateId);

        Personalization personalization = new Personalization();
        personalization.addTo(mailTo);
        personalization.addDynamicTemplateData("token", token);
        personalization.addDynamicTemplateData("username", username);

        mail.addPersonalization(personalization);

        SendGrid sg = new SendGrid(apiKey);

        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            if (response.getStatusCode() != 202) {
                throw new FailedToSendEmailException("failed to send email to recover password");
            }
        } catch (Exception e) {
            throw new FailedToSendEmailException("failed to send email to recover password");
        }
    }
}
