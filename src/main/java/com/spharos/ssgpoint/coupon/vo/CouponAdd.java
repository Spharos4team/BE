package com.spharos.ssgpoint.coupon.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class CouponAdd {

    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String store;
    private String image;
    private String content;

    public CouponAdd(String title, String description, LocalDate startDate, LocalDate endDate, String store, String image, String content) {
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.store = store;
        this.image = image;
        this.content = content;
    }

    public boolean isValidDateRange() {
        return !this.startDate.isAfter(this.endDate);
    }
}
