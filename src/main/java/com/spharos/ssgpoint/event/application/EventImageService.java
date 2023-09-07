package com.spharos.ssgpoint.event.application;

import com.spharos.ssgpoint.event.domain.EventImageList;

public interface EventImageService {

    // Event에 EventImageList 추가
    void addEventImageListToEvent(Long eventId, EventImageList eventImageList);

    // EventImage에 EventImageList 추가
    void addEventImageListToEventImage(Long eventImageId, EventImageList eventImageList);

    // EventImageList 추가
    EventImageList addEventImageList(EventImageList eventImageList);

    // EventImageList 삭제
    void removeEventImageList(Long eventImageListId);

    // 다른 비즈니스 로직 메서드들의 시그니처...
}
