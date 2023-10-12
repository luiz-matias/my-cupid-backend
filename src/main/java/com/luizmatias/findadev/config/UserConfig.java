package com.luizmatias.findadev.config;

import com.luizmatias.findadev.db.repositories.UserDatabaseRepository;
import com.luizmatias.findadev.db.repositories.UserJpaRepository;
import com.luizmatias.findadev.domain.repositories.NotificationSenderRepository;
import com.luizmatias.findadev.domain.repositories.PasswordEncoder;
import com.luizmatias.findadev.domain.repositories.UserRepository;
import com.luizmatias.findadev.domain.usecases.auth.ActivateUserInteractor;
import com.luizmatias.findadev.domain.usecases.auth.CreateUserTemporaryTokenInteractor;
import com.luizmatias.findadev.domain.usecases.auth.VerifyUserTemporaryTokenInteractor;
import com.luizmatias.findadev.domain.usecases.user.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    CreateUserInteractor createUserInteractor(UserRepository userRepository, PasswordEncoder passwordEncoder, CreateUserTemporaryTokenInteractor createUserTemporaryTokenInteractor, NotificationSenderRepository notificationSenderRepository) {
        return new CreateUserInteractor(userRepository, passwordEncoder, createUserTemporaryTokenInteractor, notificationSenderRepository);
    }

    @Bean
    ActivateUserInteractor activateUserInteractor(UserRepository userRepository, VerifyUserTemporaryTokenInteractor verifyUserTemporaryTokenInteractor) {
        return new ActivateUserInteractor(userRepository, verifyUserTemporaryTokenInteractor);
    }

    @Bean
    GetUserInteractor getUserInteractor(UserRepository userRepository) {
        return new GetUserInteractor(userRepository);
    }

    @Bean
    GetAllUsersInteractor getAllUsersInteractor(UserRepository userRepository) {
        return new GetAllUsersInteractor(userRepository);
    }

    @Bean
    UpdateUserInteractor updateUserInteractor(UserRepository userRepository) {
        return new UpdateUserInteractor(userRepository);
    }

    @Bean
    DeleteUserInteractor deleteUserInteractor(UserRepository userRepository) {
        return new DeleteUserInteractor(userRepository);
    }

    @Bean
    UserRepository userRepository(UserJpaRepository userJpaRepository) {
        return new UserDatabaseRepository(userJpaRepository);
    }

    @Bean
    ChangePasswordInteractor changePasswordInteractor(CreateUserTemporaryTokenInteractor createUserTemporaryTokenInteractor, NotificationSenderRepository notificationSenderRepository, PasswordEncoder passwordEncoder) {
        return new ChangePasswordInteractor(createUserTemporaryTokenInteractor, notificationSenderRepository, passwordEncoder);
    }

    @Bean
    ConfirmChangePasswordInteractor confirmChangePasswordInteractor(VerifyUserTemporaryTokenInteractor verifyUserTemporaryTokenInteractor, ChangeUserPasswordInteractor changeUserPasswordInteractor) {
        return new ConfirmChangePasswordInteractor(verifyUserTemporaryTokenInteractor, changeUserPasswordInteractor);
    }

    @Bean
    ChangeUserPasswordInteractor changeUserPasswordInteractor(UserRepository userRepository, PasswordEncoder passwordEncoder, NotificationSenderRepository notificationSenderRepository) {
        return new ChangeUserPasswordInteractor(userRepository, passwordEncoder, notificationSenderRepository);
    }

}
