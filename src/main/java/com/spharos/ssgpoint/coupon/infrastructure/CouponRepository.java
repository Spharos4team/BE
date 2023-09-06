package com.spharos.ssgpoint.coupon.infrastructure;

import com.spharos.ssgpoint.coupon.domain.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;  // 이 부분을 수정했습니다.

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    @Query("SELECT c FROM Coupon c WHERE c.number = :number")
    Optional<Coupon> findByNumber(@Param("number") String number);

    @Query("SELECT c FROM Coupon c WHERE c.startDate <= :currentDate AND c.endDate >= :currentDate")
    List<Coupon> findAvailableCoupons(@Param("currentDate") LocalDate currentDate);

    List<Coupon> findAllByEndDateBefore(LocalDate endDate);



}
