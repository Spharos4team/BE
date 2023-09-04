package com.spharos.ssgpoint.coupon.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
public class CouponAdd {

    private final String title;
    private final String description;
    private final Date startDate;
    private final Date endDate;
    private final String store;
    private final String image;
    private final String content;

    public boolean isValidDateRange() {
        return !startDate.after(endDate);
    }

    // 기타 필요한 메서드들...
}
