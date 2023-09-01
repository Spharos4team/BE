package com.spharos.ssgpoint.event.dto;

import com.spharos.ssgpoint.event.domain.Event;
import com.spharos.ssgpoint.event.domain.EventImage;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventImageListDto {

    private Long id;
    private Event event;
    private EventImage eventImage;

}
