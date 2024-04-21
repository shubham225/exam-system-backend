package com.exam.system.controllers;

import com.exam.system.dtos.user.UserResponseDto;
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
        return userService.getUserDetails(id);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "{username}"
    )
    public UserResponseDto getUserById(@PathVariable String username) {
        return userService.getUserDetails(username);
    }
}
