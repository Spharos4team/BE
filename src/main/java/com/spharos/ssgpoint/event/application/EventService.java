package com.spharos.ssgpoint.event.application;

import com.spharos.ssgpoint.event.domain.Event;
import com.spharos.ssgpoint.event.infrastructure.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EventService { // 이벤트 서비스

    private final EventRepository eventRepository; // 이벤트 레포지토리

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    // src/main/java/com/spharos/ssgpoint/event/service/EventService.java

// ... 기존 코드 ...

    public List<Event> getEventsByType(String eventType) { // 이벤트 타입에 따른 이벤트 목록 조회
        return switch (eventType) { // 이벤트 타입에 따라 분기
            case "진행중" -> eventRepository.findByType(0);  // 0은 예시입니다. 실제 값은 DB 설정에 따름
            case "마감" -> eventRepository.findByType(1); // 1은 예시입니다. 실제 값은 DB 설정에 따름
            case "당첨" -> eventRepository.findByType(2); // 2는 예시입니다. 실제 값은 DB 설정에 따름
            default -> throw new IllegalArgumentException("잘못된 이벤트입니다."); // 잘못된 이벤트 타입
        };
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    } // 이벤트 상세 조회

    public Event uploadEvent(String title, String content, LocalDateTime startDate, LocalDateTime endDate, String relatedLink, MultipartFile image) {
        String imageUrl = uploadImage(image); // 이미지 업로드

        Event event = Event.builder().title(title).contentImageUrl(content).startDate(startDate).endDate(endDate).contentImageUrl(relatedLink).thumbnailUrl(imageUrl).build(); // Event 객체 생성

        return eventRepository.save(event); // DB에 저장
    }

    private String uploadImage(MultipartFile image) {
        // TODO: 이미지 업로드 로직 구현
        return "uploaded_image_url"; // 임시 URL
    }

}