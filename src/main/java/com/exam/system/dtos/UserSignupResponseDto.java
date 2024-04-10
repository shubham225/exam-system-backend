package com.exam.system.dtos;

import com.exam.system.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserSignupResponseDto extends UserDto {
    private String  password;

    public UserSignupResponseDto() {}
    public UserSignupResponseDto(User user) {
        super.setUsername(user.getUsername());
        this.password = user.getPassword();
    }
}
