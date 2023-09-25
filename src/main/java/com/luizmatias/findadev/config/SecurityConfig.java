package com.luizmatias.findadev.config;

import com.luizmatias.findadev.api.controllers.auth.AuthController;
import com.luizmatias.findadev.api.controllers.user.UserController;
import com.luizmatias.findadev.api.security.SecurityFilter;
import com.luizmatias.findadev.domain.entities.UserRole;
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
    public com.luizmatias.findadev.domain.repositories.PasswordEncoder domainPasswordEncoder(PasswordEncoder passwordEncoder) {
        return new com.luizmatias.findadev.api.security.BCryptPasswordEncoder(passwordEncoder);
    }

}
