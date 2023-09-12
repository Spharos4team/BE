package com.spharos.ssgpoint.pointgift.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointGiftUserGetDto {

    private String loginId;
    private String name;
    private String phone;

}
