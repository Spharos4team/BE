package com.spharos.ssgpoint.user.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordUpdateDto {

    private String password;
}
