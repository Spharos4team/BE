package com.spharos.ssgpoint.coupon.presentation;

import com.spharos.ssgpoint.coupon.application.CouponService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/coupon")
public class CouponController {

    private final CouponService couponService;
    private final StoreManager storeManager;

    public CouponController(CouponService couponService, StoreManager storeManager) {
        this.couponService = couponService;
        this.storeManager = storeManager;
    }

    @PostMapping("/add")
    public void addExternalCoupon(@RequestBody CouponRequest request) {
        couponService.addExternalCoupon(request.getCouponNumber());
    }

    @GetMapping("/generate")
    public CouponResponse generateCoupon(@RequestParam String storeName) {
        String couponNumber = storeManager.generateCouponNumber(storeName);
        return new CouponResponse(couponNumber);
    }

    static class CouponRequest {
        private String couponNumber;

        public String getCouponNumber() {
            return couponNumber;
        }

        public void setCouponNumber(String couponNumber) {
            this.couponNumber = couponNumber;
        }
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