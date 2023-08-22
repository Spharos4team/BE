package com.spharos.ssgpoint.event.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false)
    private String thumbnailUrl; // S3나 다른 스토리지 서비스에 저장된 이미지의 URL

    @Column(nullable = false)
    private String contentImageUrl; // S3나 다른 스토리지 서비스에 저장된 이미지의 URL

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Type type; // 이벤트 타입

    @Column(nullable = false)
    private LocalDateTime startDate; // 이벤트 시작일

    @Column(nullable = false)
    private LocalDateTime endDate; // 이벤트 종료일

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserEvent> userEventLists; // 이벤트 참여자 목록


    public enum Type {
        ONGOING, CLOSED, WINNER  // 진행중, 종료, 당첨자 발표
    }
}