package com.spharos.ssgpoint.event.application;

import com.spharos.ssgpoint.event.domain.Event;
import com.spharos.ssgpoint.event.domain.EventImage;
import com.spharos.ssgpoint.event.domain.EventImageList;
import com.spharos.ssgpoint.event.dto.EventImageListDao;
import com.spharos.ssgpoint.event.dto.EventImageListDto;
import com.spharos.ssgpoint.event.infrastructure.EventImageListRepository;
import com.spharos.ssgpoint.event.infrastructure.EventImageRepository;
import com.spharos.ssgpoint.event.infrastructure.EventRepository;
import com.spharos.ssgpoint.user.domain.User;
import com.spharos.ssgpoint.user.infrastructure.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventImageListServiceImple implements EventImageListService {

    private final EventRepository eventRepository;
    private final EventImageRepository eventImageRepository;
    private final EventImageListRepository eventImageListRepository;

    @Override
    public void addEventImageList(EventImageListDao eventImageListDao) {
        Event event = eventRepository.findById(eventImageListDao.getEvent().getId()).orElse(null);
        EventImage eventImage = eventImageRepository.findById(eventImageListDao.getEventImage().getId()).orElse(null);

        eventImageListRepository.save(EventImageList.builder()
                .event(event)
                .eventImage(eventImage)
                .build());
    }

    @Override
    public EventImageListDto getEventImageListById(Long id) {
        return EventImageListDto.builder()
                .id(id)
                .event(eventRepository.findById(id).orElse(null))
                .eventImage(eventImageRepository.findById(id).orElse(null))
                .build();
    }

    @Override
    public List<EventImageListDto> getEventImageListByEventId(Long eventId) {
        return null;
    }

    @Override
    public List<EventImageListDto> getEventImageListByUserId(Long userId) {
        return null;
    }
}
