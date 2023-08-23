package com.spharos.ssgpoint.point.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointGetVo {

    private Integer point;
    private Integer totalPoint;
    private String pointTitle;
    private String pointContent;
    private Integer status;

}
