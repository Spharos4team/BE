package com.spharos.ssgpoint.receipt.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReceiptGetDto {

    private String storeName;
    private Integer point;

}
