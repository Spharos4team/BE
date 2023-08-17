package com.spharos.ssgpoint.coupon.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCouponDto {

    private Long id;
    private Long userId;
    private Long couponId;
    private boolean used;
    private LocalDateTime downloadDate;  // 발급 날짜로 변경
    private LocalDateTime usedDate;      // 사용 날짜
}
