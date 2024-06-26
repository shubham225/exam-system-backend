package com.exam.system.controllers;

import com.exam.system.dtos.auth.LoginRequestDto;
import com.exam.system.dtos.auth.LoginResponseDto;
import com.exam.system.dtos.user.UserSignupRequestDto;
import com.exam.system.dtos.user.UserSignupResponseDto;
import com.exam.system.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/V1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/login"
    )
    public ResponseEntity<LoginResponseDto> userLogin(@RequestBody LoginRequestDto loginRequest) {
        LoginResponseDto response =  authService.authenticateUser(loginRequest);
        return ResponseEntity.ok(response);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/logout"
    )
    public ResponseEntity<LoginResponseDto> userLogout() {
        // TODO: Logout will invalidate the token
        LoginResponseDto response =  new LoginResponseDto();
        return ResponseEntity.ok(response);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/register"
    )
    public UserSignupResponseDto signUpUser(@RequestBody UserSignupRequestDto userSignupRequestDto) {
        return authService.userSignup(userSignupRequestDto);
    }
}
