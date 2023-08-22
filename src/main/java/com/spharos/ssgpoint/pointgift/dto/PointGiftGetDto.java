package com.spharos.ssgpoint.pointgift.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointGiftGetDto {

    private Integer point;
    private String message;
    private Integer access;
    private String UUID;

}
