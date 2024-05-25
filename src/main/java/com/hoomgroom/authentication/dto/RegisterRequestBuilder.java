package com.hoomgroom.authentication.dto;

public class RegisterRequestBuilder {
    String fullName;
    String dateOfBirth;
    String gender;
    String username;
    String email;
    String password;
    String role;

    public RegisterRequestBuilder fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public RegisterRequestBuilder dateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public RegisterRequestBuilder gender(String gender) {
        this.gender = gender;
        return this;
    }

    public RegisterRequestBuilder username(String username) {
        this.username = username;
        return this;
    }

    public RegisterRequestBuilder email(String email) {
        this.email = email;
        return this;
    }

    public RegisterRequestBuilder password(String password) {
        this.password = password;
        return this;
    }

    public RegisterRequestBuilder role(String role) {
        this.role = role;
        return this;
    }

    public RegisterRequest build() {
        return new RegisterRequest(this);
    }
}
