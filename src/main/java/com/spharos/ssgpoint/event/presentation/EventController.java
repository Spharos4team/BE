package com.spharos.ssgpoint.event.presentation;

import com.spharos.ssgpoint.event.application.EventService;
import com.spharos.ssgpoint.event.application.S3Service;
import com.spharos.ssgpoint.event.domain.Event;
import com.spharos.ssgpoint.event.vo.EventAdd;
import com.spharos.ssgpoint.event.vo.EventOut;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
        // ... 나머지 코드 ...
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

    @GetMapping("/events/all")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/events/participated")
    public ResponseEntity<List<Event>> getEventsParticipatedByUser(@RequestParam String uuid) {
        List<Event> events = eventService.getEventsByType(uuid);
        return ResponseEntity.ok(events);
    }

    @GetMapping("/events/winning")
    public ResponseEntity<List<Event>> getWinningEventsByUuid(@RequestParam String uuid) {
        List<Event> events = eventService.getEventsByType(uuid);
        return ResponseEntity.ok(events);
    }


    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        String imageUrl = s3Service.uploadFile(file);
        return ResponseEntity.ok(imageUrl);
    }


}