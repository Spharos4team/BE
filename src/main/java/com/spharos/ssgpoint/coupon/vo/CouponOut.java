package com.spharos.ssgpoint.coupon.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class CouponOut {

    private Long couponId;
    private String uuid;

    public boolean isValidUUID() {
        return uuid != null && !uuid.trim().isEmpty();
    }
}
