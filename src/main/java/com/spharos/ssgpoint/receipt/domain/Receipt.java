package com.spharos.ssgpoint.receipt.domain;

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
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String associate;

    @Column(nullable = false, length = 100)
    private String brand;

    @Column(nullable = false, length = 100)
    private String storeName;

    @Column(nullable = false, length = 100)
    private String number;

    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer amount;

    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer point;

}
