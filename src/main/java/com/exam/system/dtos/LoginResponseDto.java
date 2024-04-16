package com.exam.system.dtos;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class LoginResponseDto {
    private long userId;
    private String username;
    private String token;

    public LoginResponseDto() {}

    public LoginResponseDto(long userId, String username, String token) {
        this.userId = userId;
        this.username = username;
        this.token = token;
    }
}
