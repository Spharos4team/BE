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
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45, nullable = true)
    private String title;

    @Column(nullable = false)
    private int number;

    @Column(length = 255, nullable = true)
    private String barCode;

    @Column(length = 45)
    private String store;
}
