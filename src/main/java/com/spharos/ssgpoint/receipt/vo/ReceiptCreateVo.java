package com.spharos.ssgpoint.receipt.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptCreateVo {

    private String alliance;
    private String brand;
    private String storeName;
    private String number;
    private Integer amount;
    private Integer point;

}
