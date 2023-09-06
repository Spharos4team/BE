package com.spharos.ssgpoint.coupon.application;

import com.spharos.ssgpoint.coupon.dto.CouponDto;
import com.spharos.ssgpoint.coupon.dto.UserCouponDto;
import com.spharos.ssgpoint.coupon.vo.CouponAdd;

import java.util.List;

public interface CouponService {
    void registerCoupon(CouponAdd couponAdd);

    void deleteCoupon(Long couponId);


    List<CouponDto> getAvailableCoupons();

    List<UserCouponDto> getMyCoupons(String uuid);

    List<CouponDto> getExpiredCoupons();

    CouponDto getCouponById(Long couponId);

    void assignCoupon(String uuid, Long couponId);
}
