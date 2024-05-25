package com.hoomgroom.authentication.dto;

import lombok.*;

import java.time.LocalDate;

import enums.Gender;
import enums.Role;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserData {
    private String fullName;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String username;
    private String email;
    private Role role;
    private double walletBalance;

    UserData(UserDataBuilder builder) {
        this.fullName = builder.fullName;
        this.dateOfBirth = builder.dateOfBirth;
        this.gender = builder.gender;
        this.username = builder.username;
        this.email = builder.email;
        this.role = builder.role;
        this.walletBalance = builder.walletBalance;
    }
}
