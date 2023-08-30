package com.spharos.ssgpoint.event.domain;

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
public class UserEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY)
    private Event event;

    @ManyToOne
    private String UUID;
}

