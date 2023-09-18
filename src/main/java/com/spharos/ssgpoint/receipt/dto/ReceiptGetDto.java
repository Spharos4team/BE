package com.spharos.ssgpoint.receipt.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptGetDto {

    private String alliance;
    private String brand;
    private String storeName;
    private String cardNumber;
    private Integer point;
    private String pointCardNumber;

}
