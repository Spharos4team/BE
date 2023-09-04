package com.spharos.ssgpoint.coupon.infrastructure;

import com.spharos.ssgpoint.coupon.domain.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserCouponRepository extends JpaRepository<UserCoupon, Long> {
    Optional<UserCoupon> findByUuidAndCouponId(String uuid, Long couponId);
    // 기타 메서드들...

    List<UserCoupon> findAllByUuid(String uuid);

    // 추가적인 쿼리 메소드 필요 시 여기에 정의
}
