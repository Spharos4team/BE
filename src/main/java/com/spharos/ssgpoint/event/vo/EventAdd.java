package com.spharos.ssgpoint.event.vo;

import lombok.Getter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@ToString
public class EventAdd {

    private String title;
    private String content;
    private String eventType;

    private String thumbnailUrl;
    private List<String> eventImages;

    private Date startDate;
    private Date endDate;

}
