package com.hoomgroom.authentication.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private String email;
    String password;

    public LoginRequest(LoginRequestBuilder builder) {
        this.email = builder.email;
        this.password = builder.password;
    }
}
