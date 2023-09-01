package com.spharos.ssgpoint.event.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {

    private String title;
    private String content;
    private String eventType;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String thumbnailUrl;
    private List<String> eventImages;

    public EventDto(String title, String content, String name, String thumbnailUrl, List<String> eventImages) {
    }
}