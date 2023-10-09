package com.luizmatias.findadev.domain.entities;

public enum TokenType {
    RECOVER_PASSWORD("RECOVER_PASSWORD"),
    ACTIVATE_ACCOUNT("ACTIVATE_ACCOUNT");

    private String type;

    TokenType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
