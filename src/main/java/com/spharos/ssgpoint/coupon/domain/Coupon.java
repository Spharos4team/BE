package com.spharos.ssgpoint.coupon.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

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

    private boolean isActive;  // New field to check if the coupon is active or not

    public boolean isValid() {
        Date now = new Date();
        return isActive && !now.before(startDate) && !now.after(endDate);
    }
    

}