package com.spharos.ssgpoint.coupon.infrastructure;

import com.spharos.ssgpoint.coupon.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> { // 쿠폰 레포지토리
    // 추가적인 쿼리 메소드 필요 시 여기에 정의
}
