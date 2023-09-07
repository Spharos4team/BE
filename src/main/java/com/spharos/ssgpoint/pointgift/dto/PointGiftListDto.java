package com.spharos.ssgpoint.pointgift.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.spharos.ssgpoint.point.domain.PointStatusType;
import com.spharos.ssgpoint.point.domain.PointType;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString
@NoArgsConstructor
public class PointGiftListDto {

    private Long pointId;
    private Integer point;
    private String title;
    private String content;
    private PointType type;
    private PointStatusType statusType;
    private LocalDateTime createdDate;

    @QueryProjection
    public PointGiftListDto(Long pointId, Integer point, String title, String content, PointType type,
                            PointStatusType statusType, LocalDateTime createdDate) {
        this.pointId = pointId;
        this.point = point;
        this.title = title;
        this.content = content;
        this.type = type;
        this.statusType = statusType;
        this.createdDate = createdDate;
    }
}
