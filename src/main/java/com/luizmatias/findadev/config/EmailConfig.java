package com.luizmatias.findadev.config;

import com.luizmatias.findadev.domain.repositories.NotificationSenderRepository;
import com.luizmatias.findadev.email.SendGridNotificationSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {

    @Bean
    NotificationSenderRepository emailSenderRepository() {
        return new SendGridNotificationSender();
    }

}
