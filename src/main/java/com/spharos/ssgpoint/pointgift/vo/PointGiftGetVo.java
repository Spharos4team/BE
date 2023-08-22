package com.spharos.ssgpoint.pointgift.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointGiftGetVo {

    private Integer point;
    private String message;
    private String access;
    private String UUID;

}
