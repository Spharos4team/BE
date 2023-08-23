package com.spharos.ssgpoint.coupon.application;

import com.spharos.ssgpoint.coupon.domain.Coupon;
import com.spharos.ssgpoint.coupon.domain.UserCoupon;
import com.spharos.ssgpoint.coupon.infrastructure.CouponRepository;
import com.spharos.ssgpoint.coupon.infrastructure.UserCouponRepository;
import com.spharos.ssgpoint.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;
    private final UserCouponRepository userCouponRepository;

    @Override
    public Coupon createCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    @Override
    public Optional<Coupon> getCouponById(Long id) {
        return couponRepository.findById(id);
    }

    @Override
    public List<Coupon> getAllAvailableCoupons() {
        // TODO: Define criteria for 'available' and modify this method accordingly
        return couponRepository.findAll();
    }

    @Override
    public UserCoupon assignCouponToUser(User uuid, Coupon coupon) {
        // 사용자에게 쿠폰이 이미 할당되어 있는지 확인합니다.
        Optional<UserCoupon> existingUserCoupon = userCouponRepository.findByUUIDAndCoupon(uuid, coupon);
        if (existingUserCoupon.isPresent()) {
            throw new RuntimeException("유저가 이미 해당 쿠폰을 가지고 있습니다.");
        }

        // 쿠폰을 사용자에게 할당합니다.
        return userCouponRepository.save(UserCoupon.builder()
                .UUID(uuid)
                .coupon(coupon)
                .build());
    }

    // More methods based on requirements...
}
