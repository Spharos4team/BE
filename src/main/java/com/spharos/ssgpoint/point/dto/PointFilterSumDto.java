package com.spharos.ssgpoint.point.dto;


import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PointFilterSumDto {
    private Integer savePoint;
    private Integer usePoint;


}
