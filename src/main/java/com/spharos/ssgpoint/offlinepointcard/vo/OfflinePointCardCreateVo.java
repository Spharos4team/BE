package com.spharos.ssgpoint.offlinepointcard.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OfflinePointCardCreateVo {

    private String number;
    private Integer CVC;
    private String alliance;
    private String store;
    private Integer status;

}
