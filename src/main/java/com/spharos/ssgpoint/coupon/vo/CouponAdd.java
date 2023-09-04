package com.spharos.ssgpoint.coupon.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class CouponAdd {

    private final String title;
    private final String description;
    private final LocalDate startDate;  // java.time.LocalDate로 변경
    private final LocalDate endDate;    // java.time.LocalDate로 변경
    private final String store;
    private final String image;
    private final String content;

    public boolean isValidDateRange() {
        return !startDate.isAfter(endDate);
    }
}
