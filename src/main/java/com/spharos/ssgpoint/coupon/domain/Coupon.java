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

    private String number;

    @Column(length = 45, nullable = false)
    private String title;

    @Column(length = 45, nullable = false)
    private String description;

    private String image;

    private String storeImage1;

    private String storeImage2;

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
        return this.isActive && !this.isUsed && !this.startDate.isAfter(now) && !this.endDate.isBefore(now);
    }
}

