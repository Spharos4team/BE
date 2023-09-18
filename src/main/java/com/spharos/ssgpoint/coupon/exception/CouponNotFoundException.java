package com.spharos.ssgpoint.coupon.exception;

public class CouponNotFoundException extends RuntimeException {
    public CouponNotFoundException(Long couponId) {
        super("해당 ID(" + couponId + ")를 가진 쿠폰을 찾을 수 없습니다.");
    }
}