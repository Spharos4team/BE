package com.spharos.ssgpoint.pointgift.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointGiftCreateVo {

    private Integer point;
    private String message;
    private String type;
    private String status;

    private String loginId;
    private String name;

}
