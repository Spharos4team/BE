package com.spharos.ssgpoint.event.dto;

import com.spharos.ssgpoint.event.domain.Event;
import com.spharos.ssgpoint.event.domain.EventImage;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventImageListDao {

    private Event event;
    private EventImage eventImage;

}
