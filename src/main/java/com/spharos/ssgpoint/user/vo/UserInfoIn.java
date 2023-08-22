package com.spharos.ssgpoint.user.vo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
public class UserInfoIn {
    private String address;
    private String email;
}
