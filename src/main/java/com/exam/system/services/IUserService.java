package com.exam.system.services;

import com.exam.system.dtos.user.UserResponseDto;
import com.exam.system.dtos.user.UserSignupRequestDto;
import com.exam.system.dtos.user.UserSignupResponseDto;
import com.exam.system.models.User;

import java.util.List;

public interface IUserService {
    UserSignupResponseDto userSignup(UserSignupRequestDto request);
    UserResponseDto getUserDetails(long id);
    UserResponseDto getUserDetails(String username);

    User getUserByUsername(String username);

    List<UserResponseDto> getAllUsers();
}
