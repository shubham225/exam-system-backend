package com.exam.system.dtos;

import com.exam.system.models.Role;
import com.exam.system.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserSignupResponseDto {
    private String  username;
    private String  password;
    private Set<Role> roles;

    public UserSignupResponseDto() {}
    public UserSignupResponseDto(User user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.roles = user.getRoles();
    }
}
