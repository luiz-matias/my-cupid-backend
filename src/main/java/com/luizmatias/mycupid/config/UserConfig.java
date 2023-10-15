package com.luizmatias.mycupid.config;

import com.luizmatias.mycupid.db.repositories.UserDatabaseRepository;
import com.luizmatias.mycupid.db.repositories.UserJpaRepository;
import com.luizmatias.mycupid.domain.repositories.InterestRepository;
import com.luizmatias.mycupid.domain.repositories.NotificationSenderRepository;
import com.luizmatias.mycupid.domain.repositories.PasswordEncoder;
import com.luizmatias.mycupid.domain.repositories.UserRepository;
import com.luizmatias.mycupid.domain.usecases.auth.ActivateUserInteractor;
import com.luizmatias.mycupid.domain.usecases.auth.CreateUserTemporaryTokenInteractor;
import com.luizmatias.mycupid.domain.usecases.auth.VerifyUserTemporaryTokenInteractor;
import com.luizmatias.mycupid.domain.usecases.user.*;
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

    @Bean
    AddUserInterestInteractor addUserInterestInteractor(UserRepository userRepository, InterestRepository interestRepository) {
        return new AddUserInterestInteractor(userRepository, interestRepository);
    }

    @Bean
    RemoveUserInterestInteractor removeUserInterestInteractor(UserRepository userRepository) {
        return new RemoveUserInterestInteractor(userRepository);
    }
}
