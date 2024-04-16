package com.exam.system.services;

import com.exam.system.dtos.LoginRequestDto;
import com.exam.system.dtos.LoginResponseDto;
import com.exam.system.dtos.UserSignupRequestDto;
import com.exam.system.dtos.UserSignupResponseDto;
import com.exam.system.models.User;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
public class AuthService {
    private final JwtEncoder jwtEncoder;
    private final AuthenticationManager authenticationManager;
    private final IUserService userService;

    public AuthService(JwtEncoder jwtEncoder,
                       AuthenticationManager authenticationManager,
                       UserService userService) {
        this.jwtEncoder = jwtEncoder;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    public String generateAuthToken(Authentication authentication) {
        Instant now = Instant.now();
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet jwtClaims = JwtClaimsSet.builder()
                .issuer(authentication.getName())
                .issuedAt(now)
                .expiresAt(now.plus(2, ChronoUnit.HOURS))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(jwtClaims)).getTokenValue();
    }

    public LoginResponseDto authenticateUser(LoginRequestDto request) {
        Authentication authentication = authenticationManager.authenticate(
                                                new UsernamePasswordAuthenticationToken(
                                                        request.getUsername(),
                                                        request.getPassword()));

        User user = userService.getUserByUsername(request.getUsername());
        String token = generateAuthToken(authentication);

        return new LoginResponseDto(user.getId(), user.getUsername(), token);
    }

    public UserSignupResponseDto userSignup(UserSignupRequestDto userSignupRequestDto) {
        return userService.userSignup(userSignupRequestDto);
    }
}
