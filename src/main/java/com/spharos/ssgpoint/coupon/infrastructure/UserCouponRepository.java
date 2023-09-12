package com.spharos.ssgpoint.coupon.infrastructure;

import com.spharos.ssgpoint.coupon.domain.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserCouponRepository extends JpaRepository<UserCoupon, Long> {
    Optional<UserCoupon> findByUuidAndCouponId(@Param("uuid") String uuid, @Param("couponId") Long couponId);
    // 기타 메서드들...

    List<UserCoupon> findAllByUuid(@Param("uuid") String uuid);

    List<UserCoupon> findAllByCouponId(@Param("couponId") Long couponId);




}
