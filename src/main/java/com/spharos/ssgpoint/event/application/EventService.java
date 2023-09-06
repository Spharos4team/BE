package com.spharos.ssgpoint.event.application;

import com.spharos.ssgpoint.event.domain.Event;
import com.spharos.ssgpoint.event.domain.EventEntries;
import com.spharos.ssgpoint.event.dto.EventDto;
import com.spharos.ssgpoint.event.vo.EventAdd;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface EventService {
    List<Event> getEventsByType(String eventType);

    Event getEventById(Long id);

    EventDto addEvent(EventAdd eventAdd);

    boolean addAllEvent(
           MultipartFile thumbFile,
           List<MultipartFile> otherFiles,
           String title,
           String content
    ) throws IOException;



    List<Event> getAllEvents();

    List<EventEntries> getEventsParticipatedByUser(String uuid);

    List<EventEntries> getWinningEventsByUuid(String uuid);

    List<EventDto> getEventsList();





}
