package com.spharos.ssgpoint.coupon.presentation;

import com.spharos.ssgpoint.coupon.application.CouponService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/coupon")
public class CouponController {

    private final CouponService couponService;
    private final StoreManager storeManager = new StoreManager();

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @GetMapping("/generate")
    public CouponResponse generateCoupon(@RequestParam String storeName) {
        String couponNumber = storeManager.generateCouponNumber(storeName);
        return new CouponResponse(couponNumber);
    }

    static class CouponResponse {
        private final String couponNumber;

        public CouponResponse(String couponNumber) {
            this.couponNumber = couponNumber;
        }

        public String getCouponNumber() {
            return couponNumber;
        }
    }
}
