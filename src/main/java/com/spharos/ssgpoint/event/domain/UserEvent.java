package com.spharos.ssgpoint.event.domain;

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
public class UserEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 100, nullable = false)
    private String uuid;

    @Column(nullable = false, columnDefinition = "int default 0")
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // 외래 키 컬럼명을 user_id로 설정
    private User user; // User와의 관계 설정

    @ManyToOne(fetch = FetchType.LAZY)
    private Event event;


}