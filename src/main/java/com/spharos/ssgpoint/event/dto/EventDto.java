package com.spharos.ssgpoint.event.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {

    private String title;
    private String content;
    private String eventType;
    private Date startDate;
    private Date endDate;
    private String thumbnailUrl;
    private List<String> eventImages;

    public EventDto(String title, String content, String name, String thumbnailUrl, List<String> eventImages) {
    }
}