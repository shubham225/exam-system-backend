package com.exam.system.dtos.user;

import com.exam.system.enums.Degree;
import com.exam.system.enums.Gender;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserSignupRequestDto {
    private String fullName;
    private String email;
    private String password;
    private String gender;
    private String institute;
    private int degree;
    private String address;
}
