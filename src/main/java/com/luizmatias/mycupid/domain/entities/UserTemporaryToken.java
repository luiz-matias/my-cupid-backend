package com.luizmatias.mycupid.domain.entities;

import java.util.Date;

public class UserTemporaryToken {

    private Long id;
    private User user;
    private String token;
    private TokenType tokenType;
    private Date expiresAt;

    public UserTemporaryToken() {
    }

    public UserTemporaryToken(Long id, User user, String token, TokenType tokenType, Date expiresAt) {
        this.id = id;
        this.user = user;
        this.token = token;
        this.tokenType = tokenType;
        this.expiresAt = expiresAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }
}
