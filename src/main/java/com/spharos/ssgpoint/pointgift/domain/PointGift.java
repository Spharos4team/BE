package com.spharos.ssgpoint.pointgift.domain;

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
public class PointGift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String message;

    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer access;

    @Column(nullable = false, length = 100)
    private String UUID;

    @Column(nullable = false, length = 45)
    private String loginId;

}
