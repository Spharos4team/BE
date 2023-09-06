package com.spharos.ssgpoint.event.application;

import java.util.List;

public interface EventImageService {

    void addEventImage(String imageUrl);

    List<String> getEventImageById(Long id);
}
