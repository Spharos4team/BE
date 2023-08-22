package com.spharos.ssgpoint.coupon.application;

import com.spharos.ssgpoint.coupon.domain.Coupon;
import com.spharos.ssgpoint.coupon.domain.UserCoupon;
import com.spharos.ssgpoint.user.domain.User;

import java.util.List;
import java.util.Optional;

/*
 아직 연결안됨
 */
public interface CouponService {

    Coupon createCoupon(Coupon coupon);

    Optional<Coupon> getCouponById(Long id);

    List<Coupon> getAllAvailableCoupons();

    UserCoupon assignCouponToUser(User user, Coupon coupon);

    // More method signatures based on requirements...
}
