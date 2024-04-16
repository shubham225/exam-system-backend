package com.exam.system.services;

import com.exam.system.dtos.UserSignupRequestDto;
import com.exam.system.dtos.UserSignupResponseDto;
import com.exam.system.models.User;

public interface IUserService {
    public User createUser(UserSignupRequestDto request, String role);
    UserSignupResponseDto userSignup(UserSignupRequestDto request);
    public User getUserByUsername(String username);
}