package com.spharos.ssgpoint.pointgift.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointGiftCreateDto {

    private Integer point;
    private String message;
    private String type;
    private String access;
    private String UUID;
    private String loginId;
    private String name;

}
