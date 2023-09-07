package com.spharos.ssgpoint.event.presentation;

import com.spharos.ssgpoint.event.application.EventService;
import com.spharos.ssgpoint.event.domain.Event;
import com.spharos.ssgpoint.event.domain.EventEntries;
import com.spharos.ssgpoint.event.domain.EventType;
import com.spharos.ssgpoint.event.exception.EventException;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class EventController {

    private final EventService eventService;

    @GetMapping("/events")
    public ResponseEntity<?> getEvents(
            @RequestParam(required = false) String type,
            @RequestParam(required = false, defaultValue = "false") boolean all
    ) {
        if (all || type == null) {
            List<Event> events = eventService.getAllEvents();
            return ResponseEntity.ok(events);
        } else {
            EventType eventType = EventType.valueOf(type.toUpperCase());
            List<Event> events = eventService.getEventsByType(eventType);
            return ResponseEntity.ok(events);
        }
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
    public ResponseEntity<String> addEvent(
            @RequestParam("thumbFile") MultipartFile thumbFile,
            @RequestParam("otherFiles") List<MultipartFile> otherFiles,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime endDate,
            @RequestParam("winningDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime winningDate
    ) {
        try {
            boolean isAdded = eventService.addEvent(thumbFile, otherFiles, title, content, startDate, endDate, winningDate);
            if (isAdded) {
                return ResponseEntity.ok("이벤트가 성공적으로 추가되었습니다.");
            } else {
                return ResponseEntity.badRequest().body("이벤트 추가 중 오류가 발생했습니다.");
            }
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body("날짜 형식이 잘못되었습니다.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("이미지 업로드 중 오류가 발생했습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 내부 오류가 발생했습니다.");
        }
    }


    @GetMapping("/events/participated")
    public ResponseEntity<List<EventEntries>> getEventsParticipatedByUuid(@RequestParam String uuid) {
        List<EventEntries> eventEntries = eventService.getEventsParticipatedByUuid(uuid);
        return ResponseEntity.ok(eventEntries);
    }

    @GetMapping("/events/winning")
    public ResponseEntity<List<EventEntries>> getWinningEventsByUuid(@RequestParam String uuid) {
        List<EventEntries> winningEvents = eventService.getWinningEventsByUuid(uuid);
        return ResponseEntity.ok(winningEvents);
    }


    @ExceptionHandler(EventException.class)
    public ResponseEntity<String> handleEventException(EventException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
