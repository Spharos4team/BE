package com.spharos.ssgpoint.event.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventListResponseDto {
    private Long id;
    private String title;
    private String Type; // 진행중, 마감, 당첨 등
    // 필요한 다른 필드들...
}
