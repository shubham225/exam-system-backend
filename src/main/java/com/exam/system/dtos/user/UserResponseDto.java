package com.exam.system.dtos.user;

import com.exam.system.models.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private long userId;
    private String userName;

    public UserResponseDto() {}
    public UserResponseDto(User user) {
        this.userId = user.getId();
        this.userName = user.getUsername();
    }
}
