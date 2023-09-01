package com.spharos.ssgpoint.point.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointGetVo {

    private Integer point;
    private Integer totalPoint;
    private String title;
    private String content;
    private String type;
    private LocalDateTime createdDate;

}
