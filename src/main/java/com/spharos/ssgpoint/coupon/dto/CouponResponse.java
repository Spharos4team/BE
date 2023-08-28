package com.spharos.ssgpoint.coupon.dto;

public class CouponResponse {
    private final String couponNumber;

    public CouponResponse(String couponNumber) {
        this.couponNumber = couponNumber;
    }

    public String getCouponNumber() {
        return couponNumber;
    }
}
