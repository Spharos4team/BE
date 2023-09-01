package com.spharos.ssgpoint.event.vo;

import lombok.*;

import java.util.Date;
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
    private List<String> eventImages;
    private Date regDate;
    private Date startDate;
    private Date endDate;

}
