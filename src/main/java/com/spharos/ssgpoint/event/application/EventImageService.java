package com.spharos.ssgpoint.event.application;

import com.spharos.ssgpoint.event.domain.EventImage;

public interface EventImageService {

    void addEventImage(String imageUrl);
    EventImage getEventImageById(Long id);

}
