package com.spharos.ssgpoint.user.dto;

import lombok.*;

import java.util.Map;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpDto {
    private String loginId;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String address;
    private Map<String, Boolean> term;
}
