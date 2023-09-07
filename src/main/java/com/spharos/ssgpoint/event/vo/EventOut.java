package com.spharos.ssgpoint.event.vo;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EventOut {

    private Long eventId;

    private String title;

    private String content;

    private String eventType;

    private String thumbnailUrl;

    private String bannerUrl;

    private List<String> eventImages;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private LocalDateTime winningDate;


}
