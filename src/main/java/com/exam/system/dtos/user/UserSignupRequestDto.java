package com.exam.system.dtos.user;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserSignupRequestDto {
    private String username;
    private String password;
}
