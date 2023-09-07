package com.spharos.ssgpoint.point.vo;

import com.spharos.ssgpoint.point.domain.PointStatusType;
import com.spharos.ssgpoint.point.domain.PointType;

import java.time.LocalDateTime;

public class PointFilterOutVo {
    private Long pointId;
    private Integer point;
    private String title;
    private String content;
    private PointType type;
    private PointStatusType statusType;
    private LocalDateTime createdDate;
    private Long receiptId;

}
