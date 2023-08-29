package com.spharos.ssgpoint.point.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointGetDto {

    private Integer availablePoint;
    private Integer expectedPoint;
    private Integer point;
    private String title;
    private String content;
    private String type;
    private LocalDate createdDate;

}
