package com.spharos.ssgpoint.pointcard.domain;

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
public class PointCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45)
    private String name;

    @Column(length = 100)
    private String number;

    @Column(nullable = false, length = 20)
    private String agency;

    // @Column(nullable = false, length = 100)
    private String UUID;

    @Column(nullable = false)
    @Convert(converter = PointCardTypeConverter.class)
    private PointCardType type;

}
