package com.spharos.ssgpoint.coupon.dto;

import com.spharos.ssgpoint.coupon.domain.UserCoupon;
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
    private String uuid;
    private Long couponId;
    private boolean used;

    public UserCouponDto(UserCoupon userCoupon) {
        this.id = userCoupon.getId();
        this.uuid = userCoupon.getUuid();
        this.couponId = userCoupon.getCoupon().getId();
        this.used = userCoupon.isUsed();
    }

    // 기타 getter, setter, 기본 생성자 등...
}