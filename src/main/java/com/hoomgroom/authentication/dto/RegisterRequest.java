package com.hoomgroom.authentication.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String fullName;
    private String dateOfBirth;
    private String gender;
    private String username;
    private String email;
    private String password;
    private String role;

    RegisterRequest(RegisterRequestBuilder builder) {
        this.fullName = builder.fullName;
        this.dateOfBirth = builder.dateOfBirth;
        this.gender = builder.gender;
        this.username = builder.username;
        this.email = builder.email;
        this.password = builder.password;
        this.role = builder.role;
    }
}
