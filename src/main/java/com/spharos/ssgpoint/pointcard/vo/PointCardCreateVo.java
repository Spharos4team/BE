package com.spharos.ssgpoint.pointcard.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointCardCreateVo {

    private String number;
    private String agency;
    private String UUID;
    private String type;

}
