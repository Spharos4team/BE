package com.spharos.ssgpoint.event.presentation;

import com.spharos.ssgpoint.event.application.EventService;
import com.spharos.ssgpoint.event.application.S3Service;
import com.spharos.ssgpoint.event.domain.Event;
import com.spharos.ssgpoint.event.domain.EventEntries;
import com.spharos.ssgpoint.event.dto.EventDto;
import com.spharos.ssgpoint.event.vo.EventAdd;
import com.spharos.ssgpoint.event.vo.EventOut;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EventController {

    private final EventService eventService;
    private final S3Service s3Service;

    public EventController(EventService eventService, S3Service s3Service) {
            this.eventService = eventService;
            this.s3Service = s3Service;
        }

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getEventsByType(@RequestParam String type) {
        List<Event> events = eventService.getEventsByType(type);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/events/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Event event = eventService.getEventById(id);

        if (event == null) {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
        return ResponseEntity.ok(event); // 200 OK
    }

    @PostMapping("/events")
    public ResponseEntity<EventOut> addEvent(@RequestBody EventAdd eventAdd) {
        EventDto createdEvent = eventService.addEvent(eventAdd);
        EventOut eventOut = new EventOut(
                null, // 이벤트 ID는 데이터베이스에서 생성된 ID를 사용해야 합니다.
                createdEvent.getTitle(),
                createdEvent.getContent(),
                createdEvent.getEventType(),
                createdEvent.getThumbnailUrl(),
                createdEvent.getEventImages(),
                new Date(), // 현재 시간을 등록 날짜로 설정
                eventAdd.getStartDate(),
                eventAdd.getEndDate()
        );
        return ResponseEntity.ok(eventOut);
    }


    @GetMapping("/events/all")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/events/participated")
    public ResponseEntity<List<EventEntries>> getEventsParticipatedByUser(@RequestParam String uuid) {
        List<EventEntries> eventEntries = eventService.getEventsParticipatedByUser(uuid);
        return ResponseEntity.ok(eventEntries);
    }

    @GetMapping("/events/winning")
    public ResponseEntity<List<Event>> getWinningEventsByUuid(@RequestParam String uuid) {
        List<Event> events = eventService.getEventsByType(uuid);
        return ResponseEntity.ok(events);
    }

    @PostMapping("/events/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        String imageUrl = s3Service.uploadFile(file);
        return ResponseEntity.ok(imageUrl);
    }


}