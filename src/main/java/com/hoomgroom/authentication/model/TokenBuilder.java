package com.hoomgroom.authentication.model;

import enums.TokenType;

public class TokenBuilder {
    String token;
    TokenType tokenType;
    boolean expired;
    boolean revoked;
    User user;

    public TokenBuilder() {}

    public TokenBuilder token(String token) {
        this.token = token;
        return this;
    }

    public TokenBuilder tokenType(TokenType tokenType) {
        this.tokenType = tokenType;
        return this;
    }

    public TokenBuilder expired(boolean expired) {
        this.expired = expired;
        return this;
    }

    public TokenBuilder revoked(boolean revoked) {
        this.revoked = revoked;
        return this;
    }

    public TokenBuilder user(User user) {
        this.user = user;
        return this;
    }

    public Token build() {
        return new Token(this);
    }
}
