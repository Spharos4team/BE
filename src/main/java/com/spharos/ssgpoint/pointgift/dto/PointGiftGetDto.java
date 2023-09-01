package com.spharos.ssgpoint.pointgift.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointGiftGetDto {

    private Integer point;
    private String message;
    private String type;
    private String status;
    private String UUID;
    private String loginId;
    private String name;
    private LocalDate createdDate;

}
