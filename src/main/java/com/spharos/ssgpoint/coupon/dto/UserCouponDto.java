package com.spharos.ssgpoint.coupon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCouponDto {

    private Long id;
    private Long UUID;
    private Long couponId;
    private boolean used;
}
