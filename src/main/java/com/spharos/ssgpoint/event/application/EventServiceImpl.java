// com.spharos.ssgpoint.event.application.EventServiceImpl.java

package com.spharos.ssgpoint.event.application;

import com.spharos.ssgpoint.event.domain.Event;
import com.spharos.ssgpoint.event.infrastructure.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> getEventsByType(String eventType) {
        return switch (eventType) {
            case "진행중" -> eventRepository.findByType(0);
            case "마감" -> eventRepository.findByType(1);
            case "당첨" -> eventRepository.findByType(2);
            default -> throw new IllegalArgumentException("잘못된 이벤트입니다.");
        };
    }

    @Override
    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    @Override
    public Event uploadEvent(String title, String content, LocalDateTime startDate,
                             LocalDateTime endDate, String relatedLink, MultipartFile image) {
        String imageUrl = uploadImage(image);
        Event event = Event.builder()
                .title(title)
                .contentImageUrl(content)   // 이벤트 내용 이미지 URL을 저장
                .startDate(startDate)   // 이벤트 기간을 저장
                .endDate(endDate)   // 이벤트 기간을 저장
                .contentImageUrl(relatedLink)   // 관련 링크 URL을 저장
                .thumbnailUrl(imageUrl)     // 업로드된 이미지 URL을 저장
                .build();   // 이벤트 객체 생성

        return eventRepository.save(event); // 이벤트 저장
    }

    private String uploadImage(MultipartFile image) {
        // TODO: 이미지 업로드 로직 구현  (이미지 업로드 후 이미지 URL을 리턴)
        return "uploaded_image_url";    // 임시로 업로드된 이미지 URL을 리턴
    }
}
