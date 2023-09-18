package com.spharos.ssgpoint.pointgift.domain;

import com.spharos.ssgpoint.global.BaseEntity;
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
public class PointGift extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer point;

    @Column(length = 150)
    private String message;

    @Column(nullable = false)
    @Convert(converter = PointGiftTypeConverter.class)
    private PointGiftType type;

    @Column(nullable = false)
    @Convert(converter = PointGiftStatusTypeConverter.class)
    private PointGiftStatusType status;

    @Column(nullable = false, length = 100)
    private String UUID;

    @Column(nullable = false, length = 45)
    private String loginId;

    @Column(nullable = false, length = 100)
    private String name;

    private Long pointId;

    public void update(String status) {
        this.status = PointGiftStatusType.valueOf(status);
    }
}
