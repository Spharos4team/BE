package com.spharos.ssgpoint.coupon.presentation;

import com.spharos.ssgpoint.coupon.application.CouponService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/coupon")
public class CouponController { // 쿠폰 컨트롤러

    private final CouponService couponService; // 쿠폰 서비스

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    // TODO: 여기에 핸들러 메소드 추가 (쿠폰 생성, 조회, 다운로드 등)
}
