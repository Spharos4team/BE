package com.spharos.ssgpoint.event.application;

import com.spharos.ssgpoint.event.domain.Event;
import com.spharos.ssgpoint.event.domain.EventEntries;
import com.spharos.ssgpoint.event.domain.EventType;
import com.spharos.ssgpoint.event.dto.EventDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    List<Event> getEventsByType(EventType type);

    Event getEventById(Long id);


    boolean addEvent(
            MultipartFile thumbFile,
            List<MultipartFile> otherFiles,
            String title,
            String content,
            LocalDateTime startDate,
            LocalDateTime endDate,
            LocalDateTime winningDate
    ) throws IOException;

    List<EventDto> getAllEvents();

    List<EventEntries> getEventsParticipatedByUser(String uuid);

    List<EventEntries> getWinningEventsByUuid(String uuid);

    List<EventDto> getEventsList();


}