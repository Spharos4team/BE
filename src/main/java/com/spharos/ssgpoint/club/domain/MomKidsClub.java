package com.spharos.ssgpoint.club.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MomKidsClub {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)

    private ClubList clubList;

    @Column(length = 10, nullable = false)
    private String firstChildGender;

    @Column(nullable = false)
    private LocalDate firstChildBirthDate;

    @Column( length = 10)
    private String secondChildGender;

    @Column(length = 8 )
    private LocalDate secondChildBirthDate;
}
