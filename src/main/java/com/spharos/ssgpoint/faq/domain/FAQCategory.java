package com.spharos.ssgpoint.faq.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class FAQCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private FAQCategory parentCategory;

    @OneToMany(mappedBy = "parentCategory")
    private List<FAQCategory> subCategories = new ArrayList<>();
}
