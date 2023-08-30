package com.spharos.ssgpoint.event.application;

import com.spharos.ssgpoint.event.dto.EventImageListDao;
import com.spharos.ssgpoint.event.dto.EventImageListDto;

import java.util.List;

public interface EventImageListService {

    void addEventImageList(EventImageListDao eventImageListDao);
    EventImageListDto getEventImageListById(Long id);
    List<EventImageListDto> getEventImageListByEventId(Long eventId);
    List<EventImageListDto> getEventImageListByUserId(Long userId);

}
