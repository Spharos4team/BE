package com.spharos.ssgpoint.user.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PasswordUpdateDto {
    private String currentPassword;
    private String updatePassword;
}
