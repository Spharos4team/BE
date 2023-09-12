package com.spharos.ssgpoint.event.vo;

import com.spharos.ssgpoint.event.domain.EventImage;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EventOut {

    private Long id;
    private String title;
    private String content;

    private String eventType;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime winningDate;
    private String thumbnailUrl;
    private List<EventImage> eventImages;


}
