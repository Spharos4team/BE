package com.spharos.ssgpoint.coupon.presentation;

import com.spharos.ssgpoint.coupon.application.CouponService;
import com.spharos.ssgpoint.coupon.dto.CouponDto;
import com.spharos.ssgpoint.coupon.dto.UserCouponDto;
import com.spharos.ssgpoint.coupon.vo.CouponAdd;
import com.spharos.ssgpoint.coupon.vo.CouponOut;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CouponController {

    private final CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @PostMapping("/coupon")
    public ResponseEntity<String> registerCoupon(@RequestBody CouponAdd couponAdd) {
        couponService.registerCoupon(couponAdd);
        return ResponseEntity.ok("Coupon registered successfully.");
    }

    @GetMapping("/coupon/available")
    public ResponseEntity<List<CouponDto>> getAvailableCoupons() {
        List<CouponDto> availableCoupons = couponService.getAvailableCoupons();
        return ResponseEntity.ok(availableCoupons);
    }

    @GetMapping("/coupon")
    public ResponseEntity<List<UserCouponDto>> getMyCoupons(@RequestParam String uuid) {
        List<UserCouponDto> myCoupons = couponService.getMyCoupons(uuid);
        return ResponseEntity.ok(myCoupons);
    }

    @PostMapping("/coupon/use/{couponId}")
    public ResponseEntity<String> useCoupon(@PathVariable Long couponId) {
        couponService.useCoupon(new CouponOut(couponId, null)); // UUID is not used here, so passing null
        return ResponseEntity.ok("Coupon used successfully.");
    }

    @GetMapping("/coupon/expired")
    public ResponseEntity<List<CouponDto>> getExpiredCoupons() {
        List<CouponDto> expiredCoupons = couponService.getExpiredCoupons();
        return ResponseEntity.ok(expiredCoupons);
    }

    @DeleteMapping("/coupon/{couponId}")
    public ResponseEntity<String> deleteCoupon(@PathVariable Long couponId) {
        couponService.deleteCoupon(couponId);
        return ResponseEntity.ok("Coupon deleted successfully.");
    }
}
