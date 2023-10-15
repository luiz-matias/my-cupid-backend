package com.luizmatias.mycupid.api.security;

import com.luizmatias.mycupid.domain.repositories.RandomGenerator;

import java.security.SecureRandom;
import java.util.Base64;

public class Base64RandomGenerator implements RandomGenerator {

    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

    @Override
    public String generate() {
        byte[] randomBytes = new byte[128];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }
}
