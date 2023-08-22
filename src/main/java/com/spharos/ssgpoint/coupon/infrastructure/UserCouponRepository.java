package com.spharos.ssgpoint.coupon.infrastructure;

import com.spharos.ssgpoint.coupon.domain.Coupon;
import com.spharos.ssgpoint.coupon.domain.UserCoupon;
import com.spharos.ssgpoint.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserCouponRepository extends JpaRepository<UserCoupon, Long> { // 유저 쿠폰 레포지토리
    Optional<UserCoupon> findByUserAndCoupon(User user, Coupon coupon); // 유저와 쿠폰에 따른 유저 쿠폰 조회


    // 추가적인 쿼리 메소드 필요 시 여기에 정의
}
