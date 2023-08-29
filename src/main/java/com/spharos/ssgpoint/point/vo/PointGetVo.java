package com.spharos.ssgpoint.point.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointGetVo {

    private Integer point;
    private Integer expectedPoint;
    private Integer availablePoint;
    private String title;
    private String content;
    private String type;
    private LocalDate createdDate;

}
