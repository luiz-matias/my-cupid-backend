package com.luizmatias.mycupid.config;

import com.luizmatias.mycupid.domain.repositories.NotificationSenderRepository;
import com.luizmatias.mycupid.email.SendGridNotificationSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailConfig {

    @Bean
    NotificationSenderRepository emailSenderRepository() {
        return new SendGridNotificationSender();
    }

}
