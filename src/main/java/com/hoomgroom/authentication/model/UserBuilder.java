package com.hoomgroom.authentication.model;

import enums.Gender;
import enums.Role;

import java.time.LocalDate;

public class UserBuilder {
    String fullName;
    LocalDate dateOfBirth;
    Gender gender;
    String username;
    String email;
    String password;
    Role role;
    double walletBalance;

    public UserBuilder() {}

    public UserBuilder fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public UserBuilder dateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public UserBuilder gender (Gender gender) {
        this.gender = gender;
        return this;
    }

    public UserBuilder username(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder password(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder role(Role role) {
        this.role = role;
        return this;
    }

    public UserBuilder walletBalance(double walletBalance) {
        this.walletBalance = walletBalance;
        return this;
    }

    public User build() {
        return new User(this);
    }
}

