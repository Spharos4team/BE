package com.spharos.ssgpoint.pointcard.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointCardGetVo {

    private String name;
    private String number;
    private String agency;

}
