package com.spharos.ssgpoint.coupon.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Random;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private Integer number;

    @Column(length = 45, nullable = false)
    private String title;

    @Column(length = 45, nullable = false)
    private String description;

    private String image;

    private String content;

    private Date startDate;

    private Date endDate;

    private Date regDate;

    @Column(length = 45)
    private String store;

    private String barcode;

    public boolean isValid() {
        Date now = new Date();
        return !now.before(startDate) && !now.after(endDate);
    } // 닫는 중괄호 추가

    private String generateCouponNumber(String storePrefix) {
        if (storePrefix.length() != 8) {
            throw new IllegalArgumentException("가맹점 코드는 8자리여야 합니다.");
        }

        Random random = new Random();
        String randomDigits = String.format("%012d", random.nextLong() % 1000000000000L);

        return storePrefix + randomDigits;
    }

}