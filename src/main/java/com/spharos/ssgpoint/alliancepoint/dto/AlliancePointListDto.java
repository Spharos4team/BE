package com.spharos.ssgpoint.alliancepoint.dto;

import com.spharos.ssgpoint.point.domain.PointStatusType;
import com.spharos.ssgpoint.point.domain.PointType;
import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlliancePointListDto {
    private Long id;
    private Integer point;
    private String title;
    private String content;
    private PointType type;
    private PointStatusType statusType;
    private LocalDateTime createdDate;
}
