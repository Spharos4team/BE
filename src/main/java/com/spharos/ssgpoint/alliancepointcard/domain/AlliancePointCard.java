package com.spharos.ssgpoint.alliancepointcard.domain;

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
public class AlliancePointCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String number;

    @Column(nullable = false, length = 100)
    private String UUID;

    @Column(nullable = false)
    @Convert(converter = AlliancePointCardTypeConverter.class)
    private AlliancePointCardType type;

}
