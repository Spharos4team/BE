package com.spharos.ssgpoint.event.vo;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventAdd {

    private String title;

    private String content;

    private String eventType;

    private String thumbnailUrl;

    private List<String> eventImages;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private LocalDateTime winningDate;



}
