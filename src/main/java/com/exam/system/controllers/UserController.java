package com.exam.system.controllers;

import com.exam.system.dtos.UserResponseDto;
import com.exam.system.dtos.UserSignupRequestDto;
import com.exam.system.dtos.UserSignupResponseDto;
import com.exam.system.services.IUserService;
import com.exam.system.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/V1/user")
public class UserController {
    private final IUserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "{id}"
    )
    public UserResponseDto getUserById(@PathVariable long id) {
        return new UserResponseDto();
    }
}
