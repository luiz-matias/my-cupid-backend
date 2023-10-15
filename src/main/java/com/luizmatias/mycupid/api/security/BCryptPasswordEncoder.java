package com.luizmatias.mycupid.api.security;

import com.luizmatias.mycupid.domain.repositories.PasswordEncoder;

public class BCryptPasswordEncoder implements PasswordEncoder {

    private final org.springframework.security.crypto.password.PasswordEncoder passwordEncoder;

    public BCryptPasswordEncoder(org.springframework.security.crypto.password.PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String encode(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(String rawPassword, String password) {
        return passwordEncoder.matches(rawPassword, password);
    }
}
