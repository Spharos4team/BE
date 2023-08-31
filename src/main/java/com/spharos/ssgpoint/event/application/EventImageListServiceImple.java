package com.spharos.ssgpoint.event.application;

import com.spharos.ssgpoint.event.domain.Event;
import com.spharos.ssgpoint.event.domain.EventImage;
import com.spharos.ssgpoint.event.domain.EventImageList;
import com.spharos.ssgpoint.event.dto.EventImageListDao;
import com.spharos.ssgpoint.event.dto.EventImageListDto;
import com.spharos.ssgpoint.event.infrastructure.EventImageListRepository;
import com.spharos.ssgpoint.event.infrastructure.EventImageRepository;
import com.spharos.ssgpoint.event.infrastructure.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventImageListServiceImple implements EventImageListService {

    private final EventRepository eventRepository;
    private final EventImageRepository eventImageRepository;
    private final EventImageListRepository eventImageListRepository;

    public void addEventImageList(EventImageListDao eventImageListDao) {
        Event event = eventRepository.findById(eventImageListDao.getEvent().getId()).orElseThrow(() -> new IllegalArgumentException("다음 이미지를 찾을 수 없습니다: " + eventImageListDao.getEvent().getId()));

        EventImage eventImage = eventImageRepository.findById(eventImageListDao.getEventImage().getId()).orElseThrow(() -> new IllegalArgumentException("다음 이벤트 이미지를 찾을 수 없습니다: " + eventImageListDao.getEventImage().getId()));

        eventImageListRepository.save(EventImageList.builder().event(event).eventImage(eventImage).build());
    }

    @Override
    public EventImageListDto getEventImageListById(Long id) {
        Event event = eventRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Event not found with id: " + id));

        EventImage eventImage = eventImageRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("EventImage not found with id: " + id));

        return EventImageListDto.builder().id(id).event(event).eventImage(eventImage).build();
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
