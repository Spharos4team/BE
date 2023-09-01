package com.spharos.ssgpoint.pointgift.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointGiftGetVo {

    private Integer point;
    private String message;
    private String type;
    private String status;
    private String UUID;
    private String loginId;
    private String name;
    private LocalDate createdDate;

}
