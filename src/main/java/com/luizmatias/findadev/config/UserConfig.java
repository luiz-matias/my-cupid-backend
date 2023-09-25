package com.luizmatias.findadev.config;

import com.luizmatias.findadev.db.repositories.UserDatabaseRepository;
import com.luizmatias.findadev.db.repositories.UserJpaRepository;
import com.luizmatias.findadev.domain.repositories.PasswordEncoder;
import com.luizmatias.findadev.domain.repositories.UserRepository;
import com.luizmatias.findadev.domain.usecases.user.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    CreateUserInteractor createUserInteractor(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return new CreateUserInteractor(userRepository, passwordEncoder);
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
}
