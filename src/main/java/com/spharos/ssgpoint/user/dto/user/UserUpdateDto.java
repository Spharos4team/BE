package com.spharos.ssgpoint.user.dto.user;

import lombok.*;


@Getter @Setter
@NoArgsConstructor
public class UserUpdateDto {
    private String address;
    private String email;

    @Builder
    public UserUpdateDto(String address, String email) {
        this.address = address;
        this.email = email;
    }
}
