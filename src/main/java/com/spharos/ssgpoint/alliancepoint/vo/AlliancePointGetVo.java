package com.spharos.ssgpoint.alliancepoint.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlliancePointGetVo {

    private Integer point;
    private String type;

}
