package com.spharos.ssgpoint.event.presentation;

import com.spharos.ssgpoint.event.application.EventService;
import com.spharos.ssgpoint.event.domain.Event;
import com.spharos.ssgpoint.event.domain.EventType;
import com.spharos.ssgpoint.event.domain.UserEvent;
import com.spharos.ssgpoint.event.dto.UserEventDTO;
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
import java.util.stream.Collectors;

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
            List<Event> events = eventService.getAllEvents();
            List<Event> filteredEvents = events.stream()
                    .filter(event -> event.hasEventType(eventType))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(filteredEvents);
        }
    }


    @GetMapping("/events/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Event event = eventService.getEventById(id);
        return event != null ? ResponseEntity.ok(event) : ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    @PostMapping("/events")
    public ResponseEntity<String> addEvent(
            @RequestParam(value = "bannerFile", required = false) MultipartFile bannerFile,
            @RequestParam("thumbFile") MultipartFile thumbFile,
            @RequestParam(value = "otherFiles", required = false) List<MultipartFile> otherFiles,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime endDate,
            @RequestParam(value = "winningDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime winningDate
    ) {
        try {
            boolean isAdded = eventService.addEvent(bannerFile, thumbFile, otherFiles, title, content, startDate, endDate, winningDate);
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
    public ResponseEntity<List<UserEvent>> getEventsParticipatedByUuid(@RequestParam String uuid) {
        List<UserEvent> eventEntries = eventService.getEventsParticipatedByUuid(uuid);
        return ResponseEntity.ok(eventEntries);
    }

    @GetMapping("/events/winning")
    public ResponseEntity<List<UserEventDTO>> getWinningEventsByUuid(@RequestParam String uuid) {
        List<UserEventDTO> winningEvents = eventService.getWinningEventsByUuid(uuid);
        return ResponseEntity.ok(winningEvents);
    }

    @ExceptionHandler(EventException.class)
    public ResponseEntity<String> handleEventException(EventException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @PostMapping("/events/{eventId}/assign")
    public ResponseEntity<String> assignUserToEvent(
            @PathVariable Long eventId,
            @RequestParam String uuid) {
        try {
            eventService.assignEventToUuid(uuid, eventId);
            return ResponseEntity.ok("UUID가 이벤트에 할당되었습니다.");
        } catch (EventException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 내부 오류가 발생했습니다.");
        }
    }

    @PostMapping("/events/{eventId}/assign/winner")
    public ResponseEntity<String> assignWinnerToEvent(
            @PathVariable Long eventId,
            @RequestParam String uuid) {
        try {
            eventService.assignWinnerToUuid(uuid, eventId);
            return ResponseEntity.ok("UUID가 이벤트 당첨자로 할당되었습니다.");
        } catch (EventException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 내부 오류가 발생했습니다.");
        }
    }

}