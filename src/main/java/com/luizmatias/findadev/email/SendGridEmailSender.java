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
    private static final String recoverPasswordTemplateId = "d-9ea0cdfbe03c4eea984375ff615954f6";

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
            if (response.getStatusCode() != 200) {
                throw new FailedToSendEmailException("failed to send email to recover password");
            }
        } catch (Exception e) {
            throw new FailedToSendEmailException("failed to send email to recover password");
        }
    }

    @Override
    public void sendPasswordRecoveryEmail(String token, String to) throws FailedToSendEmailException {
        Email mailFrom = new Email(applicationEmail);
        Email mailTo = new Email(to);
        Mail mail = new Mail();

        mail.setFrom(mailFrom);
        mail.setTemplateId(recoverPasswordTemplateId);

        Personalization personalization = new Personalization();
        personalization.addTo(mailTo);
        personalization.addDynamicTemplateData("token", token);

        mail.addPersonalization(personalization);

        SendGrid sg = new SendGrid(apiKey);

        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            if (response.getStatusCode() != 200) {
                throw new FailedToSendEmailException("failed to send email to recover password");
            }
        } catch (Exception e) {
            throw new FailedToSendEmailException("failed to send email to recover password");
        }
    }
}
