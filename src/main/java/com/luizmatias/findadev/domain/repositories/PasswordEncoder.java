package com.luizmatias.findadev.domain.repositories;

public interface PasswordEncoder {
    String encode(String rawPassword);
    boolean matches(String rawPassword, String password);
}
