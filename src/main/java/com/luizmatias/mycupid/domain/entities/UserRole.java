package com.luizmatias.mycupid.domain.entities;

public enum UserRole {
    USER("USER"),
    ADMIN("ADMIN");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
