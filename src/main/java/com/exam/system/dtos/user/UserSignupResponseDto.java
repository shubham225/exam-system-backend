package com.exam.system.dtos.user;

import com.exam.system.models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserSignupResponseDto {
    private long userId;
    private String username;

    public UserSignupResponseDto() {}
    public UserSignupResponseDto(User user) {
        this.userId = user.getId();
        this.username = user.getUsername();
    }
}
