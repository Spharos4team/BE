package com.spharos.ssgpoint.coupon.infrastructure;

import com.spharos.ssgpoint.coupon.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    Optional<Coupon> findByNumber(int number);

    @Query("SELECT c FROM Coupon c WHERE c.endDate > :endDate AND c.startDate < :startDate")
    List<Coupon> findAvailableCoupons(LocalDate endDate, LocalDate startDate);

    List<Coupon> findAllByEndDateBefore(LocalDate endDate);
}
