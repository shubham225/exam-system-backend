package com.exam.system.services;

import com.exam.system.dtos.user.UserResponseDto;
import com.exam.system.dtos.user.UserSignupRequestDto;
import com.exam.system.dtos.user.UserSignupResponseDto;
import com.exam.system.models.User;

public interface IUserService {
    UserSignupResponseDto userSignup(UserSignupRequestDto request);
    UserResponseDto getUserDetails(long id);
    UserResponseDto getUserDetails(String username);
}
