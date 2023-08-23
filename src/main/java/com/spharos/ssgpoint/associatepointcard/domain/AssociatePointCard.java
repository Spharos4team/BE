package com.spharos.ssgpoint.associatepointcard.domain;

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
public class AssociatePointCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String number;

    @Column(nullable = false, length = 100)
    private String UUID;

    @Column(nullable = false)
    @Convert(converter = AssociatePointCardTypeConverter.class)
    private AssociatePointCardType type;

}
