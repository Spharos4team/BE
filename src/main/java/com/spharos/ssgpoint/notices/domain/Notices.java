package com.spharos.ssgpoint.notices.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notices {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notices_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column( nullable = false, updatable = false)
    private Date regDate = new Date();

}