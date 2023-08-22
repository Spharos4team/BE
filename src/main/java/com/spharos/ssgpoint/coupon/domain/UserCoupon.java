package com.spharos.ssgpoint.coupon.domain;

import com.spharos.ssgpoint.user.domain.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @Column(nullable = false)
    private boolean used;

    @Column(nullable = true)
    private LocalDateTime usedDate;

    @Column(nullable = false)
    private LocalDateTime downloadDate;

    public void setUser(User user) {
    }

    public void setCoupon(Coupon coupon) {
    }
}
