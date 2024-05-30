package com.exam.system.controllers;

import com.exam.system.dtos.user.UserResponseDto;
import com.exam.system.services.IUserService;
import com.exam.system.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/V1/user")
public class UserController {
    private final IUserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = ""
    )
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
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
            path = "name/{username}"
    )
    public UserResponseDto getUserByName(@PathVariable String username) {
        return new UserResponseDto(userService.getUserByUsername(username));
    }
}
