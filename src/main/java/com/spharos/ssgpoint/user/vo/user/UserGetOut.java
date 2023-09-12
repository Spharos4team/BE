package com.spharos.ssgpoint.user.vo.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserGetOut {
    private String loginId;
    private String name;
    private String email;
    private String phone;
    private String address;

}
