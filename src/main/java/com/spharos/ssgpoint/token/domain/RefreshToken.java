package com.spharos.ssgpoint.token.domain;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "refreshToken", timeToLive = 60)
@Getter
@AllArgsConstructor
@Builder
public class RefreshToken {
    @Id
    private Long id;
    private String refreshToken;

}
