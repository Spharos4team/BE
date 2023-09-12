package com.spharos.ssgpoint.event.dto;

import com.spharos.ssgpoint.event.domain.EventType;
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
public class EventDTO {

    private Long id;
    private String title;
    private String content;

    private EventType eventType;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime winningDate;
    private String bannerUrl;
    private String thumbnailUrl;
    private List<String> eventImages;




}