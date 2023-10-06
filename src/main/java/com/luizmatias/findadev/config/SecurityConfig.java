package com.luizmatias.findadev.config;

import com.luizmatias.findadev.api.controllers.auth.AuthController;
import com.luizmatias.findadev.api.controllers.user.UserController;
import com.luizmatias.findadev.api.security.Base64RandomGenerator;
import com.luizmatias.findadev.api.security.SecurityFilter;
import com.luizmatias.findadev.db.repositories.PasswordTokenDatabaseRepository;
import com.luizmatias.findadev.db.repositories.PasswordTokenJpaRepository;
import com.luizmatias.findadev.domain.entities.UserRole;
import com.luizmatias.findadev.domain.repositories.EmailSenderRepository;
import com.luizmatias.findadev.domain.repositories.PasswordTokenRepository;
import com.luizmatias.findadev.domain.repositories.RandomGenerator;
import com.luizmatias.findadev.domain.repositories.UserRepository;
import com.luizmatias.findadev.domain.usecases.auth.RequestResetPasswordInteractor;
import com.luizmatias.findadev.domain.usecases.auth.ResetPasswordInteractor;
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
                        .requestMatchers(HttpMethod.POST, AuthController.AUTH_PATH + "/register").permitAll()
                        .requestMatchers(HttpMethod.POST, AuthController.AUTH_PATH + "/login").permitAll()
                        .requestMatchers(HttpMethod.POST, AuthController.AUTH_PATH + "/reset-password").permitAll()
                        .requestMatchers(HttpMethod.POST, AuthController.AUTH_PATH + "/request-reset-password").permitAll()
                        .requestMatchers(HttpMethod.GET, UserController.USERS_PATH + "/").hasRole(UserRole.ADMIN.getRole())
                        .requestMatchers(HttpMethod.GET, UserController.USERS_PATH).hasRole(UserRole.ADMIN.getRole())
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
    public PasswordTokenRepository passwordTokenRepository(PasswordTokenJpaRepository passwordTokenJpaRepository) {
        return new PasswordTokenDatabaseRepository(passwordTokenJpaRepository);
    }

    @Bean
    RequestResetPasswordInteractor requestResetPasswordInteractor(UserRepository userRepository, PasswordTokenRepository passwordTokenRepository, com.luizmatias.findadev.domain.repositories.PasswordEncoder passwordEncoder) {
        return new RequestResetPasswordInteractor(userRepository, passwordTokenRepository, passwordEncoder);
    }

    @Bean
    ResetPasswordInteractor resetPasswordInteractor(UserRepository userRepository, PasswordTokenRepository passwordTokenRepository, EmailSenderRepository emailSenderRepository, RandomGenerator randomGenerator) {
        return new ResetPasswordInteractor(userRepository, passwordTokenRepository, emailSenderRepository, randomGenerator);
    }

}
