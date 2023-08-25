package com.spharos.ssgpoint.club.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BizClub {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private ClubList clubList;

    private String companyName;

    private String businessRegistrationNumber;

    private String representativeName;

    private String companyAddress;

    private String email;

}
