package com.spharos.ssgpoint.point.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointCreateDto {

    private Integer totalPoint;
    private Integer point;
    private Integer status;
    private String UUID;
    private Long pointCardId;

}
