// com.spharos.ssgpoint.event.application.EventServiceImpl.java

package com.spharos.ssgpoint.event.application;

import com.spharos.ssgpoint.event.domain.Event;
import com.spharos.ssgpoint.event.domain.EventEntries;
import com.spharos.ssgpoint.event.domain.EventType;
import com.spharos.ssgpoint.event.dto.EventDto;
import com.spharos.ssgpoint.event.infrastructure.EventRepository;
import com.spharos.ssgpoint.event.infrastructure.UserEventRepository;
import com.spharos.ssgpoint.event.vo.EventAdd;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final S3Service s3Service;
    private final EventImageService eventImageService;
    private final UserEventRepository userEventRepository;

    @Override
    public List<Event> getEventsByType(String eventType) {
        return eventRepository.findByEventType(eventType);
    }

    @Override
    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("다음 이벤트를 찾을수 없습니다: " + id));
    }


    @Override
    public EventDto addEvent(@NotNull EventAdd eventAdd) {
        Event event = Event.builder().title(eventAdd.getTitle()).content(eventAdd.getContent())
                .eventType(EventType.valueOf(eventAdd.getEventType())).thumbnailUrl(eventAdd.getThumbnailUrl())
                .startDate(eventAdd.getStartDate()).endDate(eventAdd.getEndDate()).build();
        Event savedEvent = eventRepository.save(event);

        return new EventDto(savedEvent.getTitle(), savedEvent.getContent(), savedEvent.getEventType().name(), savedEvent.getThumbnailUrl(), eventAdd.getEventImages());
    }


    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public List<EventEntries> getEventsParticipatedByUser(String uuid) {
        return userEventRepository.findByUuid(uuid);
    }

    @Override
    public List<EventEntries> getWinningEventsByUuid(String uuid) {
        return userEventRepository.findByUuidAndIsWinning(uuid, true);
    }


}