package com.spharos.ssgpoint.coupon.dto;

import lombok.*;

@Getter
@Setter
public class CouponResponse {
    private final String couponNumber;

    public CouponResponse(String couponNumber) {
        this.couponNumber = couponNumber;
    }
}
