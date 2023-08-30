package com.spharos.ssgpoint.event.presentation;

import com.spharos.ssgpoint.event.application.EventService;
import com.spharos.ssgpoint.event.domain.Event;
import com.spharos.ssgpoint.event.vo.EventAdd;
import com.spharos.ssgpoint.event.vo.EventOut;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/events")
    public ResponseEntity<List<Event>> getEventsByType(@RequestParam String event_type) {
        List<Event> events = eventService.getEventsByType(event_type);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/events/detail?eventNo={id}")
    public ResponseEntity<Event> getEventDetail(@RequestParam Long eventNo) {
        Event event = eventService.getEventById(eventNo);
        if (event == null) {
            return ResponseEntity.notFound().build(); // 404 Not Found

        }
        return ResponseEntity.ok(event); // 200 OK
    }

    @PostMapping
    public ResponseEntity<EventOut> uploadEvent(@RequestBody EventAdd eventAdd) {
        EventOut eventOut = new EventOut();
        return ResponseEntity.ok(eventOut);
    }
}