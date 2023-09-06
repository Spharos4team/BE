// com.spharos.ssgpoint.event.application.EventServiceImpl.java

package com.spharos.ssgpoint.event.application;

import com.spharos.ssgpoint.event.domain.Event;
import com.spharos.ssgpoint.event.domain.EventEntries;
import com.spharos.ssgpoint.event.domain.EventImage;
import com.spharos.ssgpoint.event.domain.EventType;
import com.spharos.ssgpoint.event.dto.EventDto;
import com.spharos.ssgpoint.event.infrastructure.EventImageRepository;
import com.spharos.ssgpoint.event.infrastructure.EventRepository;
import com.spharos.ssgpoint.event.infrastructure.UserEventRepository;
import com.spharos.ssgpoint.event.vo.EventAdd;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventImageRepository eventImageRepository;
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
    @Transactional
    public boolean addAllEvent(MultipartFile thumbFile, List<MultipartFile> otherFiles, String title, String content) throws IOException {

        try {
            String thumbImageUrl = s3Service.uploadFile(thumbFile);
//        EventAdd eventAdd = EventAdd.builder()
//                .title(title)
//                .content(content)
//                .thumbnailUrl(thumbImageUrl)
//                .build();

            Event event = Event.builder().title(title).content(content)
                    .thumbnailUrl(thumbImageUrl).build();

            Event e = eventRepository.save(event);

            for(int i=0;i<otherFiles.size();i++){
                MultipartFile one = otherFiles.get(i);
                String imageUrl = s3Service.uploadFile(one);

                EventImage eventImage = EventImage.builder().
                        eventId(e.getId()).
                        imageUrl(imageUrl).build();
                eventImageRepository.save(eventImage);
            }
            return true;
        }catch (Exception e) {
            return false;
        }
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

    @Override
    public List<EventDto> getEventsList() {
        List<Event> events = eventRepository.findAll();
        return events.stream()
                .map(event -> new EventDto(event.getTitle(), event.getContent(), event.getEventType().name(), event.getThumbnailUrl(), eventImageService.getEventImageById(event.getId())))
                .collect(Collectors.toList());
    }


}