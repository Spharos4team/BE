package com.spharos.ssgpoint.coupon.exception;

public class CouponNotFoundException extends RuntimeException {
    public CouponNotFoundException(Long couponId) {
        super("Coupon with ID " + couponId + " not found.");
    }
}