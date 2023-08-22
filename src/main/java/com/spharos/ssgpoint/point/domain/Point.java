package com.spharos.ssgpoint.point.domain;

import com.spharos.ssgpoint.pointcard.domain.PointCard;
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
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer totalPoint;

    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer point;

    @Column(nullable = false, columnDefinition = "int default 1")
    private Integer status;

    @Column(nullable = false, length = 100)
    private String UUID;

    //todo: 포인트 타입

    @ManyToOne(fetch = FetchType.LAZY)
    private PointCard pointCard;

}