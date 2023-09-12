package com.spharos.ssgpoint.event.application;

import com.spharos.ssgpoint.event.domain.Event;
import com.spharos.ssgpoint.event.domain.EventImage;
import com.spharos.ssgpoint.event.infrastructure.EventImageRepository;
import com.spharos.ssgpoint.event.infrastructure.EventRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventImageServiceImpl implements EventImageService {

    private final EventRepository eventRepository;
    private final EventImageRepository eventImageRepository;

    /**
     * 이벤트 이미지를 추가합니다.
     * @param eventId
     * @param eventImages
     */
    public void addEventImage(Long eventId, List<EventImage> eventImages) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with id: " + eventId));

        for (EventImage eventImage : eventImages) {
            eventImage.setEvent(event);
            eventImageRepository.save(eventImage);
        }
    }
}
