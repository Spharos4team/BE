package com.spharos.ssgpoint.coupon.application;

import com.spharos.ssgpoint.coupon.dto.CouponDto;
import com.spharos.ssgpoint.coupon.dto.UserCouponDto;
import com.spharos.ssgpoint.coupon.vo.CouponAdd;
import com.spharos.ssgpoint.coupon.vo.CouponOut;

import java.util.List;

public interface CouponService {
    void registerCoupon(CouponAdd couponAdd);

    void deleteCoupon(Long couponId);

    void useCoupon(CouponOut couponUse);

    List<CouponDto> getAvailableCoupons();

    List<UserCouponDto> getMyCoupons(String uuid);

    List<CouponDto> getExpiredCoupons();
}
