package com.spharos.ssgpoint.event.application;

import com.spharos.ssgpoint.event.domain.Event;
import com.spharos.ssgpoint.event.domain.EventType;
import com.spharos.ssgpoint.event.domain.UserEvent;
import com.spharos.ssgpoint.event.dto.UserEventDTO;
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

    List<Event> getAllEvents();

    List<UserEvent> getEventsParticipatedByUuid(String uuid);

    List<UserEventDTO> getWinningEventsByUuid(String uuid);

    void assignEventToUuid(String uuid, Long eventId);

    void assignWinnerToUuid(String uuid, Long eventId);
}
