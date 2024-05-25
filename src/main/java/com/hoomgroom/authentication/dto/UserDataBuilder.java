package com.hoomgroom.authentication.dto;

import enums.Gender;
import enums.Role;

import java.time.LocalDate;

public class UserDataBuilder {
    String fullName;
    LocalDate dateOfBirth;
    Gender gender;
    String username;
    String email;
    Role role;
    double walletBalance;

    public UserDataBuilder() {}

    public UserDataBuilder fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public UserDataBuilder dateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
        return this;
    }

    public UserDataBuilder gender(Gender gender) {
        this.gender = gender;
        return this;
    }

    public UserDataBuilder username(String username) {
        this.username = username;
        return this;
    }

    public UserDataBuilder email(String email) {
        this.email = email;
        return this;
    }

    public UserDataBuilder role(Role role) {
        this.role = role;
        return this;
    }

    public UserDataBuilder walletBalance(double walletBalance) {
        this.walletBalance = walletBalance;
        return this;
    }

    public UserData build() {
        return new UserData(this);
    }
}
