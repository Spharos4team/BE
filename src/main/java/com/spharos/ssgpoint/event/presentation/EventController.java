// src/main/java/com/spharos/ssgpoint/event/controller/EventController.java

package com.spharos.ssgpoint.event.presentation;

import com.spharos.ssgpoint.event.domain.Event;
import com.spharos.ssgpoint.event.application.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<Event>> getEventsByType(@RequestParam String event_type) {
        List<Event> events = eventService.getEventsByType(event_type);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/detail")
    public ResponseEntity<Event> getEventDetail(@RequestParam Long eventNo) {
        Event event = eventService.getEventById(eventNo);
        if(event == null) {
            return ResponseEntity.notFound().build(); // 404 Not Found

        }
        return ResponseEntity.ok(event); // 200 OK
    }

    @PostMapping
    public ResponseEntity<Event> uploadEvent(
            @RequestParam("image") MultipartFile image,
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam Date startDate,
            @RequestParam Date endDate,
            @RequestParam String relatedLink
    ) {
        Event event = eventService.uploadEvent(title, content, startDate, endDate, relatedLink, image);
        return ResponseEntity.ok(event);
    }
}


    // 다른 매핑 메서드들도 이와 같은 방식으로 구현
