package com.hoomgroom.authentication.model;

import enums.Gender;
import enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    private String fullName;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String username;
    @Id
    private String email;
    private String password;
    private Role role;
    private double walletBalance;
}
