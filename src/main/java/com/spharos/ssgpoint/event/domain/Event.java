package com.spharos.ssgpoint.event.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

    @Column(nullable = false)
    private EventType eventType;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime winningDate;


}
