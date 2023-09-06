package com.spharos.ssgpoint.event.vo;

import lombok.*;

import java.util.Date;
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

    private Date startDate;

    private Date endDate;



}
