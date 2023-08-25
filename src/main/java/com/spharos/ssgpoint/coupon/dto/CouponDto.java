package com.spharos.ssgpoint.coupon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CouponDto {

    private Long id;
    private String title;          // 쿠폰 이름으로 변경
    private Integer number;        // 쿠폰 번호. INT 타입으로 변경
    private String barcode;        // 쿠폰 바코드
    private String store;          // 쿠폰 사용처로 이름 변경
}
