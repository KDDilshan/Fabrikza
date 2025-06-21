package com.kavindu.fabrikza.Authentication.Dto.Response;


import com.kavindu.fabrikza.Authentication.models.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String token;
    private String refreshToken;
    private String username;
    private Set<Roles> roles;
}
