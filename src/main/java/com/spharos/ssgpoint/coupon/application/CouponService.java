package com.spharos.ssgpoint.coupon.application;

import com.spharos.ssgpoint.coupon.dto.CouponDto;
import com.spharos.ssgpoint.coupon.dto.UserCouponDto;

import java.util.List;

public interface CouponService {
    void registerCoupon(CouponDto couponDto);

    List<CouponDto> getAvailableCoupons();

    List<UserCouponDto> getMyCoupons(String uuid);

    void useCoupon(Long couponId);

    List<CouponDto> getExpiredCoupons();
}
