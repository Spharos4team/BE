package com.spharos.ssgpoint.coupon.application;

import com.spharos.ssgpoint.coupon.domain.Coupon;
import com.spharos.ssgpoint.coupon.domain.UserCoupon;
import com.spharos.ssgpoint.user.domain.User;

import java.util.List;
import java.util.Optional;

public interface CouponService {

    Coupon createCoupon(Coupon coupon);

    Optional<Coupon> getCouponById(Long id);

    List<Coupon> getAllAvailableCoupons();

    UserCoupon assignCouponToUser(User user, Coupon coupon);

    void addExternalCoupon(String couponNumber); // 외부에서 제공된 쿠폰 번호를 추가하는 메서드

    // More method signatures based on requirements...
}
