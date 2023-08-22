// com.spharos.ssgpoint.event.application.EventService.java

package com.spharos.ssgpoint.event.application;

import com.spharos.ssgpoint.event.domain.Event;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public interface EventService {
    List<Event> getEventsByType(String eventType);
    Event getEventById(Long id);
    Event uploadEvent(String title, String content, LocalDateTime startDate,
                      LocalDateTime endDate, String relatedLink, MultipartFile image);
}
