package com.spharos.ssgpoint.receipt.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptCreateDto {

    private String alliance;
    private String brand;
    private String storeName;
    private String number;
    private Integer amount;
    private Integer point;

}
