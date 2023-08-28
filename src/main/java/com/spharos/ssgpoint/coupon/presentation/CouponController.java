package com.spharos.ssgpoint.coupon.presentation;

import com.spharos.ssgpoint.coupon.application.CouponService;
import com.spharos.ssgpoint.coupon.dto.CouponDto;
import com.spharos.ssgpoint.coupon.dto.UserCouponDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/coupon")
public class CouponController {

    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    // 쿠폰 등록
    @PostMapping("/register")
    public ResponseEntity<Void> registerCoupon(@RequestBody CouponDto couponDto) {
        couponService.registerCoupon(couponDto);
        return ResponseEntity.ok().build();
    }

    // 다운 받을 수 있는 쿠폰 목록 조회
    @GetMapping("/available")
    public ResponseEntity<List<CouponDto>> getAvailableCoupons() {
        List<CouponDto> availableCoupons = couponService.getAvailableCoupons();
        return ResponseEntity.ok(availableCoupons);
    }

    // 내가 보유한 쿠폰 목록 조회
    @GetMapping("/coupon?user_id={user_id}&status={available}{userId}")
    public ResponseEntity<List<UserCouponDto>> getMyCoupons(@PathVariable String uuid) {
        List<UserCouponDto> myCoupons = couponService.getMyCoupons(uuid);
        return ResponseEntity.ok(myCoupons);
    }

    // 쿠폰 사용하기
    @PostMapping("/use/{couponId}")
    public ResponseEntity<Void> useCoupon(@PathVariable Long couponId) {
        couponService.useCoupon(couponId);
        return ResponseEntity.ok().build();
    }

    // 만료된 쿠폰 조회
    @GetMapping("/coupon?user_id={user_id}&status={exp}")
    public ResponseEntity<List<CouponDto>> getExpiredCoupons() {
        List<CouponDto> expiredCoupons = couponService.getExpiredCoupons();
        return ResponseEntity.ok(expiredCoupons);
    }
}
