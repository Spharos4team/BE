package com.spharos.ssgpoint.offlinepointcard.domain;

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
public class OfflinePointCard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String number;

    private Integer CVC;

    private String alliance;

    private String store;

    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer status;

    public void update(Integer status) {
        this.status = status;
    }

}
