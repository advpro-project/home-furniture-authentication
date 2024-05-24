package com.hoomgroom.authentication.dto;

import lombok.*;

import java.time.LocalDate;

import enums.Gender;
import enums.Role;

@Getter
@Setter
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
