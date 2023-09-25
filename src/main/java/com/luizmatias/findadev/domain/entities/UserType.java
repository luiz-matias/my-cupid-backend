package com.luizmatias.findadev.domain.entities;

public enum UserType {
    CLIENT("CLIENT"),
    DEVELOPER("DEVELOPER");

    private String type;

    UserType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
