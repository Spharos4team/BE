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
    private String number;           // 쿠폰 번호
    private String store;          // 쿠폰 사용처
    private String image;          // 쿠폰 이미지
    private String storeImage1;          // 쿠폰 이미지
    private String storeImage2;          // 쿠폰 이미지
    private String description;    // 쿠폰 설명
    private boolean isUsed;        // 쿠폰 사용 여부
    private boolean isActive;      // 쿠폰 활성화 여부

    public CouponDto(Coupon coupon) {
        this.id = coupon.getId();
        this.title = coupon.getTitle();
        this.number = coupon.getNumber();
        this.store = coupon.getStore();
        this.image = coupon.getImage();
        this.storeImage1 = coupon.getStoreImage1();
        this.storeImage2 = coupon.getStoreImage2();
        this.description = coupon.getDescription();
        this.isUsed = coupon.isUsed();
        this.isActive = coupon.isActive();
    }
}
