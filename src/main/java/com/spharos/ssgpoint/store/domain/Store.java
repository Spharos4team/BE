package com.spharos.ssgpoint.store.domain;

import com.spharos.ssgpoint.alliance.domain.Alliance;
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
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String store;

    @ManyToOne(fetch = FetchType.LAZY)
    private Alliance alliance;

}
