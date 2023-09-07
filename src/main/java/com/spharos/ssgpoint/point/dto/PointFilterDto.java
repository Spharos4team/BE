package com.spharos.ssgpoint.point.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.spharos.ssgpoint.point.domain.PointStatusType;
import com.spharos.ssgpoint.point.domain.PointType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString

@NoArgsConstructor
public class PointFilterDto {
    private Long pointId;
    private Integer point;
    private String title;
    private String content;
    private PointType type;
    private PointStatusType statusType;
    private LocalDateTime createdDate;
    private Long receiptId;


    @QueryProjection
    public PointFilterDto(Long pointId, Integer point, String title, String content, PointType type, PointStatusType statusType,
                          LocalDateTime createdDate, Long receiptId) {
        this.pointId = pointId;
        this.point = point;
        this.title = title;
        this.content = content;
        this.type = type;
        this.statusType = statusType;
        this.createdDate = createdDate;
        this.receiptId = receiptId;
    }
}
