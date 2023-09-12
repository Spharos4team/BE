package com.spharos.ssgpoint.event.application;

import com.spharos.ssgpoint.event.domain.EventImage;

import java.util.List;

public interface EventImageService {
    // EventImage 추가
    void addEventImage(Long eventId, List<EventImage> eventImage);
}
