package com.spharos.ssgpoint.event.application;

import com.spharos.ssgpoint.event.domain.Event;
import com.spharos.ssgpoint.event.domain.EventEntries;
import com.spharos.ssgpoint.event.dto.EventDto;
import com.spharos.ssgpoint.event.vo.EventAdd;

import java.util.List;

public interface EventService {
    List<Event> getEventsByType(String eventType);

    Event getEventById(Long id);

    EventDto addEvent(EventAdd eventAdd);

    List<Event> getAllEvents();

    List<EventEntries> getEventsParticipatedByUser(String uuid);

    List<EventEntries> getWinningEventsByUuid(String uuid);

}
