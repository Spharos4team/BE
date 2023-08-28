package com.spharos.ssgpoint.coupon.application;

import com.spharos.ssgpoint.coupon.domain.Coupon;
import com.spharos.ssgpoint.coupon.domain.UserCoupon;

import java.util.List;

public interface CouponService {

    Coupon createCoupon(Coupon coupon);

    List<Coupon> getAllAvailableCoupons();

    UserCoupon assignCouponToUser(String UUID, Coupon coupon);

    void addExternalCoupon(String couponNumber); // 외부에서 제공된 쿠폰 번호를 추가하는 메서드

    // More method signatures based on requirements...
}
