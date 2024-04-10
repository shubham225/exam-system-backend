package com.exam.system.dtos;

import com.exam.system.models.Role;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserSignupRequestDto {
    private String  username;
    private String  password;
    private Set<String> roles;
}
