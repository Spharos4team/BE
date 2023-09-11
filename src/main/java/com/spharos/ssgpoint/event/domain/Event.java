package com.spharos.ssgpoint.event.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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

    @Column(length = 500)
    private String content;

    @Column(nullable = false)
    private String thumbnailUrl; // S3나 다른 스토리지 서비스에 저장된 이미지의 URL

    private String bannerUrl;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "event_types", joinColumns = @JoinColumn(name = "event_id"))
    @Column(name = "event_type")
    private Set<EventType> eventTypes; // 여러 EventType을 관리할 수 있는 Set 필드

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime winningDate;

    public void addEventImageList(EventImageList eventImageList) {
    }
}
