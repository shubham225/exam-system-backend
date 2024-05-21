package com.exam.system.dtos.auth;

import com.exam.system.models.Role;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class LoginResponseDto {
    private long userId;
    private String username;
    private String token;
    private Set<String> roles;

    public LoginResponseDto() {}

    public LoginResponseDto(long userId, String username, String token, Set<Role> roles) {
        this.userId = userId;
        this.username = username;
        this.token = token;

        this.roles = new HashSet<>();
        for(Role role : roles)
            this.roles.add(role.getRole());
    }
}
