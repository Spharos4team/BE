package com.spharos.ssgpoint.coupon.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

    private LocalDate startDate;  // java.time.LocalDate로 변경
    private LocalDate endDate;    // java.time.LocalDate로 변경

    @Column(length = 45)
    private String store;

    private String barcode;

    private boolean isActive;

    private boolean isUsed;

    public boolean isValid() {
        LocalDate now = LocalDate.now();  // java.time.LocalDate로 변경
        return isActive && !isUsed && !startDate.isAfter(now) && !endDate.isBefore(now);
    }
}

