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
    private String eventType; // Assuming EventType is a String. Replace with the correct type if necessary
    private String thumbnailUrl;
    private String bannerUrl;
    private List<String> eventImages; // Assuming the images are stored as URLs. Replace with the correct type if necessary
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime winningDate;

    // getters and setters
}