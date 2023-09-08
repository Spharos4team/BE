package com.spharos.ssgpoint.pointgift.vo;

import com.spharos.ssgpoint.point.domain.PointStatusType;
import com.spharos.ssgpoint.point.domain.PointType;

import java.time.LocalDateTime;

public class PointListOutVo {
    private Long pointId;
    private Integer point;
    private String title;
    private String content;
    private PointType type;
    private PointStatusType statusType;
    private LocalDateTime createdDate;

}
