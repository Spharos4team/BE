package com.spharos.ssgpoint.event.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EventDetailResponseDto {
    private Long id;
    private String title;
    private String description;
    private String Type; // 진행중, 마감, 당첨 등
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer status; // 예: 0 = 비활성, 1 = 활성
    private String imageUrl; // 이벤트 관련 이미지 URL
    // 필요한 다른 상세 필드들...

    // 생성자, getter, setter 등...
}
