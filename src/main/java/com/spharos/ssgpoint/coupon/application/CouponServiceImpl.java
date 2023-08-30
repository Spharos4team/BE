package com.spharos.ssgpoint.coupon.application;

import com.spharos.ssgpoint.coupon.domain.Coupon;
import com.spharos.ssgpoint.coupon.dto.CouponDto;
import com.spharos.ssgpoint.coupon.dto.UserCouponDto;
import com.spharos.ssgpoint.coupon.infrastructure.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 쿠폰 서비스 구현체
 */
@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;
    private final StoreManager storeManager;

    /**
     * @param couponDto 쿠폰 DTO
     */
    public void registerCoupon(CouponDto couponDto) {
        // DTO를 Entity로 변환 후 저장
    }

    /**
     * @return 쿠폰 DTO 목록
     */
    public List<CouponDto> getAvailableCoupons() {
        // DTO로 변환 후 반환
        return null;
    }

    /**
     * @param uuid 유저 UUID
     * @return
     */
    public List<UserCouponDto> getMyCoupons(String uuid) {
        // Fetch user's coupons and convert to DTO
        return null;
    }

    /**
     * @param couponId 쿠폰 ID
     */
    public void useCoupon(Long couponId) {
        // Mark the coupon as used
    }

    /**
     * @return 만료된 쿠폰 목록
     */
    public List<CouponDto> getExpiredCoupons() {
        // Fetch expired coupons and convert to DTO
        return null;
    }

    /**
     * @param couponId 쿠폰 ID
     */
    @Override
    public void deleteCoupon(Long couponId) {

    }


    /**
     * @param storeName 가맹점 이름
     * @return
     */
    @Override
    public CouponDto createCouponForStore(String storeName) {
        String couponNumber = storeManager.generateCouponNumber(storeName);

        Coupon coupon = Coupon.builder()
                .number(Integer.parseInt(couponNumber))
                .title("Generated Coupon for " + storeName)
                .description("Description for " + storeName)
                // ... 다른 필드들 ...
                .build();

        couponRepository.save(coupon);
        return null;
    }
}
