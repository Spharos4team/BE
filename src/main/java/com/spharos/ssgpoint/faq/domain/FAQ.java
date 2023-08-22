package com.spharos.ssgpoint.faq.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class FAQ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "reg_date", nullable = false)
    private LocalDateTime regDate;

    // ... Getter, Setter, Constructors
}
