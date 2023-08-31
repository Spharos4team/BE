// com.spharos.ssgpoint.event.application.EventServiceImpl.java

package com.spharos.ssgpoint.event.application;

import com.spharos.ssgpoint.event.domain.Event;
import com.spharos.ssgpoint.event.domain.EventEntries;
import com.spharos.ssgpoint.event.domain.EventType;
import com.spharos.ssgpoint.event.dto.EventDto;
import com.spharos.ssgpoint.event.infrastructure.EventRepository;
import com.spharos.ssgpoint.event.infrastructure.UserEventRepository;
import com.spharos.ssgpoint.event.vo.EventAdd;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventImageListService eventImageListService;
    private final EventImageService eventImageService;
    private final UserEventRepository userEventRepository;

    @Override
    public List<Event> getEventsByType(String eventType) {
        return eventRepository.findByEventType(eventType);
    }


    @Override
    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    public EventDto addEvent(EventAdd eventAdd) {

        eventRepository.save(Event.builder().title(eventAdd.getTitle()).content(eventAdd.getContent()).startDate(eventAdd.getStartDate()).endDate(eventAdd.getEndDate()).eventType(EventType.valueOf(eventAdd.getEventType())).thumbnailUrl(eventAdd.getThumbnailUrl()).build());


        return null;
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




//
//    @Override
//    public List<Event> getEventsByType(String eventType) {
//        return switch (eventType) {
//            case "진행중" -> eventRepository.findByType(0);
//            case "마감" -> eventRepository.findByType(1);
//            case "당첨" -> eventRepository.findByType(2);
//            default -> throw new IllegalArgumentException("잘못된 이벤트입니다.");
//        };
//    }
//
//    @Override
//    public Event getEventById(Long id) {
//        return eventRepository.findById(id).orElse(null);
//    }
//
//    @Override
//    public Event uploadEvent(String title, String content, LocalDateTime startDate,
//                             LocalDateTime endDate, String relatedLink, MultipartFile image) {
//        String imageUrl = uploadImage(image);
//        Event event = Event.builder()
//                .title(title)
//                .contentImageUrl(content)
//                .startDate(startDate)
//                .endDate(endDate)
//                .contentImageUrl(relatedLink)
//                .thumbnailUrl(imageUrl)     // 업로드된 이미지 URL을 저장
//                .build();   // 이벤트 객체 생성
//
//        return eventRepository.save(event); // 이벤트 저장
//    }
//
//    private String uploadImage(MultipartFile image) {
//        // TODO: 이미지 업로드 로직 구현  (이미지 업로드 후 이미지 URL을 리턴)
//        return "uploaded_image_url";    // 임시로 업로드된 이미지 URL을 리턴
//    }
}
