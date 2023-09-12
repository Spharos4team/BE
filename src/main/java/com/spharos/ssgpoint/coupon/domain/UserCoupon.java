package com.spharos.ssgpoint.coupon.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;

    @ManyToOne(fetch = FetchType.EAGER)
    private Coupon coupon;

    @Column(nullable = false)
    private boolean used;

    public void use() {
        if (coupon.isValid()) {
            used = true;
        } else {
            throw new IllegalStateException("쿠폰이 유효하지 않습니다.");
        }
    }
}
