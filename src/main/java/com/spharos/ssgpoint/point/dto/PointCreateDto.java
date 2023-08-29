package com.spharos.ssgpoint.point.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointCreateDto {

    private Integer expectedPoint;
    private Integer point;
    private String title;
    private String content;
    private String type;
    private String user;
    private Long pointCardId;

}
