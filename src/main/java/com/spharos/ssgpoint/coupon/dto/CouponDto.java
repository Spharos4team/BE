package com.spharos.ssgpoint.coupon.dto;

import com.spharos.ssgpoint.coupon.domain.Coupon;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CouponDto {

    private Long id;
    private String title;          // 쿠폰 이름
    private Integer number;           // 쿠폰 번호
    private String store;          // 쿠폰 사용처
    private String description;    // 쿠폰 설명
    private boolean isUsed;        // 쿠폰 사용 여부
    private boolean isActive;      // 쿠폰 활성화 여부

    public CouponDto(Coupon coupon) {
        this.id = coupon.getId();
        this.title = coupon.getTitle();
        this.number = coupon.getNumber();
        this.store = coupon.getStore();
        this.description = coupon.getDescription();
        this.isUsed = coupon.isUsed();
        this.isActive = coupon.isActive();
    }
}
