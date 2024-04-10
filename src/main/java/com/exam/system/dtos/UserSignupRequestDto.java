package com.exam.system.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserSignupRequestDto extends UserDto {
    private String password;
}
