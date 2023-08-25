package com.spharos.ssgpoint.point.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointCreateVo {

    private Integer totalPoint;
    private Integer point;
    private String pointTitle;
    private String pointContent;
    private String type;
    private String UUID;
    private Long pointCardId;

}
