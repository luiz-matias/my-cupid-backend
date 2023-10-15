package com.luizmatias.mycupid.domain.repositories;

public interface PasswordEncoder {
    String encode(String rawPassword);
    boolean matches(String rawPassword, String password);
}
