package com.spharos.ssgpoint.temporarycard.domain;

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
public class TemporaryCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 8)
    private String birth;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 19)
    private String number;

    @Column(nullable = false)
    private Integer CVC;

    @Column(nullable = false, length = 20)
    private String agency;

}
