package com.spharos.ssgpoint.alliancepoint.domain;

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
public class AlliancePoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer point;

    @Column(nullable = false)
    @Convert(converter = AlliancePointTypeConverter.class)
    private AlliancePointType type;

    @Column(nullable = false, length = 100)
    private String UUID;

    private String fromPoint;
    private String toPoint;


    public void updateMinus(Integer minusPoint) {
        this.point -= minusPoint;
    }

    public void updatePlus(Integer plusPoint) {
        this.point += plusPoint;
    }

}
