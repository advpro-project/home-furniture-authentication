package com.hoomgroom.authentication.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private String email;
    String password;
}
