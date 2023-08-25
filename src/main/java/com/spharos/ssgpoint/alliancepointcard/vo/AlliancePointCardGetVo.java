package com.spharos.ssgpoint.alliancepointcard.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlliancePointCardGetVo {

    private String number;
    private String type;

}
