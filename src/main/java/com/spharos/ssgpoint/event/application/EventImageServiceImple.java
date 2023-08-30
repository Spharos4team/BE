package com.spharos.ssgpoint.event.application;

import com.spharos.ssgpoint.event.domain.EventImage;
import com.spharos.ssgpoint.event.infrastructure.EventImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventImageServiceImple implements EventImageService {

    private final EventImageRepository eventImageRepository;

    @Override
    public void addEventImage(String imageUrl) {
        eventImageRepository.save(EventImage.builder()
                .imageUrl(imageUrl)
                .build());
    }

    @Override
    public EventImage getEventImageById(Long id) {
        EventImage eventImage = eventImageRepository.findById(id).orElse(null);
        return eventImage;
    }
}
