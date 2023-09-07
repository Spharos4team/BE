package com.spharos.ssgpoint.event.application;

import com.spharos.ssgpoint.event.domain.Event;
import com.spharos.ssgpoint.event.domain.EventImageList;
import com.spharos.ssgpoint.event.infrastructure.EventImageListRepository;
import com.spharos.ssgpoint.event.infrastructure.EventImageRepository;
import com.spharos.ssgpoint.event.infrastructure.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventImageServiceImpl implements EventImageService {


    private EventRepository eventRepository;


    private EventImageRepository eventImageRepository;


    private EventImageListRepository eventImageListRepository;

    // Event에 EventImageList 추가
    public void addEventImageListToEvent(Long eventId, EventImageList eventImageList) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with id: " + eventId));

        event.addEventImageList(eventImageList);
    }

    public EventImageList addEventImageList(EventImageList eventImageList) {
        return eventImageListRepository.save(eventImageList);
    }




}
