package com.spharos.ssgpoint.user.dto;

import lombok.*;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordResetDto {

    private String password;
}
