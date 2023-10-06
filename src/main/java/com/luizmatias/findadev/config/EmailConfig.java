package com.luizmatias.findadev.config;

import com.luizmatias.findadev.domain.repositories.EmailSenderRepository;
import com.luizmatias.findadev.email.SendGridEmailSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {

    @Bean
    EmailSenderRepository emailSenderRepository() {
        return new SendGridEmailSender();
    }

}
