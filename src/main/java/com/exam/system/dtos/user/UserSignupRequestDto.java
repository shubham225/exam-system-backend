package com.exam.system.dtos.user;

import com.exam.system.enums.Degree;
import com.exam.system.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserSignupRequestDto {
    private String username;
    private String password;
    private String fullName;
    private Gender gender;
    private String institute;
    private Degree degree;
}
