package com.spharos.ssgpoint.coupon.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class CouponUse {

    private final Long couponId;
    private final String uuid;
    private final boolean used;

    public boolean isValidUUID() {
        return uuid != null && !uuid.trim().isEmpty();
    }


}
