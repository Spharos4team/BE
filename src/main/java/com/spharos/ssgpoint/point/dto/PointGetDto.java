package com.spharos.ssgpoint.point.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointGetDto {

    private Long pointId;
    private Integer totalPoint;
    private Integer point;
    private String pointTitle;
    private String pointContent;
    private Integer status;

}
