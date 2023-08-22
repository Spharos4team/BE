package com.spharos.ssgpoint.event.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventDto {

    private Long id;

    private String title;

    private String thumbnailImage;

    private String contentImage;

    private String eventType;
}