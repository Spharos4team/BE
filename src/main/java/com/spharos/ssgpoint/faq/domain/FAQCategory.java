package com.spharos.ssgpoint.faq.domain;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

@Entity
public class FAQCategory {

    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    private FAQCategory parent;

    // ... Getter, Setter, Constructors
}
