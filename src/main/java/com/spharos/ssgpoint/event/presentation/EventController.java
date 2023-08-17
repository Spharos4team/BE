package com.spharos.ssgpoint.event.presentation;

import com.spharos.ssgpoint.event.application.EventService;
import com.spharos.ssgpoint.event.domain.Event;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

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
        if (event == null) {
            return ResponseEntity.notFound().build(); // 404 Not Found

        }
        return ResponseEntity.ok(event); // 200 OK
    }

    @PostMapping
    public ResponseEntity<Event> uploadEvent(@RequestParam("image") MultipartFile image, @RequestParam String title, @RequestParam String content, @RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate, @RequestParam String relatedLink) {
        Event event = eventService.uploadEvent(title, content, startDate, endDate, relatedLink, image);
        return ResponseEntity.ok(event); // 200 OK
    }
}