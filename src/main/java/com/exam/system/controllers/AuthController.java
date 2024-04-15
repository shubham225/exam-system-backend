package com.exam.system.controllers;

import com.exam.system.dtos.UserDto;
import com.exam.system.dtos.UserSignupRequestDto;
import com.exam.system.dtos.UserSignupResponseDto;
import com.exam.system.security.configurations.authprovider.UserAuthProvider;
import com.exam.system.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/V1/auth")
public class AuthController {
    private final UserService userService;
    private final UserAuthProvider userAuthProvider;

    public AuthController(UserService userService, UserAuthProvider userAuthProvider) {
        this.userService = userService;
        this.userAuthProvider = userAuthProvider;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/login"
    )
    public ResponseEntity<String> loginUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userAuthProvider.createToken(userDto.getUsername()));
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/register"
    )
    public UserSignupResponseDto signUpUser(@RequestBody UserSignupRequestDto userSignupRequestDto) {
        return userService.userSignup(userSignupRequestDto);
    }
}
