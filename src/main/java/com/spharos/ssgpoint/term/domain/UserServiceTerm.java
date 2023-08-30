package com.spharos.ssgpoint.term.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spharos.ssgpoint.global.BaseEntity;
import com.spharos.ssgpoint.user.domain.User;
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
public class UserServiceTerm extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean agreed;
    private String title;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    public void setAgreed(boolean agreed) {
        this.agreed = agreed;
    }


}
