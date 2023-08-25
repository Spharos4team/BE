package com.spharos.ssgpoint.coupon.infrastructure;

import com.spharos.ssgpoint.coupon.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    Optional<Coupon> findByNumber(int number);

}