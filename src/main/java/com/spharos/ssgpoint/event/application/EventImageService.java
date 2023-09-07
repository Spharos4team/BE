package com.spharos.ssgpoint.event.application;

import com.spharos.ssgpoint.event.domain.EventImageList;

public interface EventImageService {

    // Event에 EventImageList 추가
    void addEventImageListToEvent(Long eventId, EventImageList eventImageList);


    // EventImageList 추가
    EventImageList addEventImageList(EventImageList eventImageList);


}
