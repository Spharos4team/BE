package com.spharos.ssgpoint.point.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointGetDto {

    private Integer point;
    private String title;
    private String content;
    private String type;
    private String statusType;
    private LocalDateTime createdDate;

    @QueryProjection
    public PointGetDto(Integer point, String title, String content, String type, LocalDateTime createdDate) {
        this.point = point;
        this.title = title;
        this.content = content;
        this.type = type;
        this.createdDate = createdDate;
    }
}
