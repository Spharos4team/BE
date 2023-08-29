package com.spharos.ssgpoint.auth.vo;

import lombok.*;

@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RefreshTokenVo {
    private String refreshToken;
}
