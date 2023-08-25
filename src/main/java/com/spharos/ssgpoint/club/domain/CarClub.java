package com.spharos.ssgpoint.club.domain;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarClub {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 5, nullable = false)
    private String areaNumber;

    @Column(length = 3, nullable = false)
    private String carFirstPart;

    @Column(length = 3, nullable = false)
    private String carMiddlePart;

    @Column(length = 4, nullable = false)
    private String carLastPart;
}
