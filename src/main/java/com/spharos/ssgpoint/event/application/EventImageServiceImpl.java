package com.spharos.ssgpoint.event.application;

import com.spharos.ssgpoint.event.domain.EventImage;
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
//        Event event = eventRepository.findById(eventId)
//                .orElseThrow(() -> new EntityNotFoundException("Event not found with id: " + eventId));
//
//        event.addEventImageList(eventImageList);
//        eventRepository.save(event);
    }


    public EventImageList addEventImageList(EventImageList eventImageList) {
        return eventImageListRepository.save(eventImageList);
    }

    // EventImage에 EventImageList 추가
    public void addEventImageListToEventImage(Long eventImageId, EventImageList eventImageList) {
        EventImage eventImage = eventImageRepository.findById(eventImageId)
                .orElseThrow(() -> new EntityNotFoundException("EventImage not found with id: " + eventImageId));

        eventImage.addEventImageList(eventImageList);
        eventImageRepository.save(eventImage);
    }

    public void removeEventImageList(Long eventImageListId) {
        eventImageListRepository.deleteById(eventImageListId);
    }


}
