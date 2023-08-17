package com.spharos.ssgpoint.event.application;

import com.spharos.ssgpoint.event.domain.Event;
import com.spharos.ssgpoint.event.infrastructure.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService {

    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    // src/main/java/com/spharos/ssgpoint/event/service/EventService.java

// ... 기존 코드 ...

    public List<Event> getEventsByType(String eventType) {
        return switch (eventType) {
            case "진행중" -> eventRepository.findByType(0);  // 0은 예시입니다. 실제 값은 DB 설정에 따름
            case "마감" -> eventRepository.findByType(1);
            case "당첨" -> eventRepository.findByType(2);
            default -> throw new IllegalArgumentException("Invalid event type");
        };
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }

    public Event uploadEvent(String title, String content, LocalDateTime startDate, LocalDateTime endDate, String relatedLink, MultipartFile image) {
        String imageUrl = uploadImage(image);

        Event event = Event.builder().title(title).contentImageUrl(content).startDate(startDate).endDate(endDate).contentImageUrl(relatedLink).thumbnailUrl(imageUrl).build();

        return eventRepository.save(event);
    }

    private String uploadImage(MultipartFile image) {
        // TODO: 이미지 업로드 로직 구현
        return "uploaded_image_url"; // 임시 URL
    }

}