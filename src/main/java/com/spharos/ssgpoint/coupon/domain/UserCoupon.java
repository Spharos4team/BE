package com.spharos.ssgpoint.coupon.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 100, nullable = false)
    private String UUID;

    @ManyToOne(fetch = FetchType.EAGER)
    private Coupon coupon;

    @Column(nullable = false)
    private boolean used;

    public void useCoupon() {
        if (used) {
            throw new RuntimeException("쿠폰이 이미 사용되었습니다.");
        }
        this.used = true;
    }

}