package com.hoomgroom.authentication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import enums.Gender;
import enums.Role;

@Data
@Builder
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
}
