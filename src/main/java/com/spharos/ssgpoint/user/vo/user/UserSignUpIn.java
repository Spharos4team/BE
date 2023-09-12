package com.spharos.ssgpoint.user.vo.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class UserSignUpIn {


    private String loginId;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String address;
}
