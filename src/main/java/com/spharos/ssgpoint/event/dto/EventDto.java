package com.spharos.ssgpoint.event.dto;

import com.spharos.ssgpoint.event.domain.EventImage;
import com.spharos.ssgpoint.event.domain.EventType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class EventDto {

    private Long id;
    private String title;
    private String content;

    private EventType eventType;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime winningDate;
    private String thumbnailUrl;
    private List<EventImage> eventImages;



    public EventDto(Long id, String title, String content, EventType name, String thumbnailUrl, List<String> eventImageById) {
    }


}