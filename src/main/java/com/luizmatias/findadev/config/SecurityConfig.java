package com.luizmatias.findadev.config;

import com.luizmatias.findadev.api.controllers.admin.AdminController;
import com.luizmatias.findadev.api.controllers.auth.AuthController;
import com.luizmatias.findadev.api.security.Base64RandomGenerator;
import com.luizmatias.findadev.api.security.SecurityFilter;
import com.luizmatias.findadev.db.repositories.UserTemporaryTokenDatabaseRepository;
import com.luizmatias.findadev.db.repositories.UserTemporaryTokenJpaRepository;
import com.luizmatias.findadev.domain.entities.UserRole;
import com.luizmatias.findadev.domain.repositories.NotificationSenderRepository;
import com.luizmatias.findadev.domain.repositories.RandomGenerator;
import com.luizmatias.findadev.domain.repositories.UserRepository;
import com.luizmatias.findadev.domain.repositories.UserTemporaryTokenRepository;
import com.luizmatias.findadev.domain.usecases.auth.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private final SecurityFilter securityFilter;

    public SecurityConfig(SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/error").permitAll()
                        .requestMatchers(HttpMethod.POST, AuthController.AUTH_PATH + "/**").permitAll()
                        .requestMatchers(HttpMethod.GET, AdminController.ADMIN_PATH + "/**").hasAuthority(UserRole.ADMIN.getRole())
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    RandomGenerator randomGenerator() {
        return new Base64RandomGenerator();
    }

    @Bean
    public com.luizmatias.findadev.domain.repositories.PasswordEncoder domainPasswordEncoder(PasswordEncoder passwordEncoder) {
        return new com.luizmatias.findadev.api.security.BCryptPasswordEncoder(passwordEncoder);
    }

    @Bean
    public UserTemporaryTokenRepository passwordTokenRepository(UserTemporaryTokenJpaRepository userTemporaryTokenJpaRepository) {
        return new UserTemporaryTokenDatabaseRepository(userTemporaryTokenJpaRepository);
    }

    @Bean
    VerifyUserTemporaryTokenInteractor verifyUserTemporaryTokenInteractor(UserTemporaryTokenRepository userTemporaryTokenRepository) {
        return new VerifyUserTemporaryTokenInteractor(userTemporaryTokenRepository);
    }

    @Bean
    VerifyUserInteractor verifyUserInteractor() {
        return new VerifyUserInteractor();
    }

    @Bean
    CreateUserTemporaryTokenInteractor createUserTemporaryTokenInteractor(UserTemporaryTokenRepository userTemporaryTokenRepository, RandomGenerator randomGenerator) {
        return new CreateUserTemporaryTokenInteractor(userTemporaryTokenRepository, randomGenerator);
    }

    @Bean
    RequestResetPasswordInteractor requestResetPasswordInteractor(UserRepository userRepository, VerifyUserTemporaryTokenInteractor verifyUserTemporaryTokenInteractor, NotificationSenderRepository notificationSenderRepository, com.luizmatias.findadev.domain.repositories.PasswordEncoder passwordEncoder) {
        return new RequestResetPasswordInteractor(userRepository, verifyUserTemporaryTokenInteractor, notificationSenderRepository, passwordEncoder);
    }

    @Bean
    ResetPasswordInteractor resetPasswordInteractor(UserRepository userRepository, CreateUserTemporaryTokenInteractor createUserTemporaryTokenInteractor, NotificationSenderRepository notificationSenderRepository) {
        return new ResetPasswordInteractor(userRepository, createUserTemporaryTokenInteractor, notificationSenderRepository);
    }

}
