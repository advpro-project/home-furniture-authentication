package com.hoomgroom.authentication.dto;

public class LoginResponseBuilder {
    String token;
    UserData userData;

    public LoginResponseBuilder token(String token) {
        this.token = token;
        return this;
    }

    public LoginResponseBuilder userData(UserData userData) {
        this.userData = userData;
        return this;
    }

    public LoginResponse build() {
        return new LoginResponse(this);
    }
}
