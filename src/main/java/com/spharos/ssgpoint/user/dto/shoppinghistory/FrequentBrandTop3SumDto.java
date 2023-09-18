package com.spharos.ssgpoint.user.dto.shoppinghistory;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FrequentBrandTop3SumDto {
    private String alliance; // 브랜드 이름
    private Integer totalSum;  // 브랜드의 총 금액
}
