package com.hoomgroom.authentication.dto;

public class LoginRequestBuilder {
    String email;
    String password;

    public LoginRequestBuilder() {}

    public LoginRequestBuilder email(String email) {
        this.email = email;
        return this;
    }

    public LoginRequestBuilder password(String password) {
        this.password = password;
        return this;
    }

    public LoginRequest build() {
        return new LoginRequest(this);
    }
}
