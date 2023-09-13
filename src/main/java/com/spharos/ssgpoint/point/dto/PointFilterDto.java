package com.spharos.ssgpoint.point.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import com.spharos.ssgpoint.point.domain.PointStatusType;
import com.spharos.ssgpoint.point.domain.PointType;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
    @JsonFormat(pattern = "yyyy-MM-dd")
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
