package com.spharos.ssgpoint.event.application;

import com.spharos.ssgpoint.event.domain.Event;
import com.spharos.ssgpoint.event.domain.EventType;
import com.spharos.ssgpoint.event.domain.UserEvent;
import com.spharos.ssgpoint.event.dto.UserEventDTO;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Nullable;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public interface EventService {
    List<Event> getEventsByTypes(Set<EventType> types);


    Event getEventById(Long id);

    boolean addEvent(
            @Nullable MultipartFile bannerFile,
            MultipartFile thumbFile,
            @Nullable List<MultipartFile> otherFiles,
            String title,
            String content,
            LocalDateTime startDate,
            LocalDateTime endDate,
            @Nullable LocalDateTime winningDate
    ) throws IOException;

    List<Event> getAllEvents();

    List<UserEvent> getEventsParticipatedByUuid(String uuid);

    List<UserEventDTO> getWinningEventsByUuid(String uuid);

    void assignEventToUuid(String uuid, Long eventId);

    void assignWinnerToUuid(String uuid, Long eventId);
}