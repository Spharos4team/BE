package com.spharos.ssgpoint.auth.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
public class AuthenticationResponse {

    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("refresh_token")
    private String refreshToken;
    private String uuid;
    private User user;

    @Data
    @Builder
    @AllArgsConstructor
    public static class User {

        private String bardCode;
        private String name;
        private Integer point;
        private String uuid;
    }

}