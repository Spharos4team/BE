package com.spharos.ssgpoint.event.application;

import com.spharos.ssgpoint.event.domain.*;
import com.spharos.ssgpoint.event.exception.EventException;
import com.spharos.ssgpoint.event.infrastructure.EventImageListRepository;
import com.spharos.ssgpoint.event.infrastructure.EventImageRepository;
import com.spharos.ssgpoint.event.infrastructure.EventRepository;
import com.spharos.ssgpoint.event.infrastructure.UserEventRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventImageRepository eventImageRepository;
    private final S3Service s3Service;
    private final UserEventRepository userEventRepository;
    private final EventImageListRepository eventImageListRepository;

    @Override
    public List<Event> getEventsByType(EventType type) {
        List<Event> result;
        switch (type) {
            case ONGOING:
                result = eventRepository.findOngoingEvents();
                break;
            case CLOSED:
                result = eventRepository.findClosedEvents();
                break;
            case WINNER:
                result = eventRepository.findWinnerEvents();
                break;
            default:
                throw new IllegalArgumentException("Invalid EventType: " + type);
        }
        return result;
    }

    @Override
    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("다음 이벤트를 찾을수 없습니다: " + id));
    }


    @Transactional
    public boolean addEvent(
            MultipartFile thumbFile,
            List<MultipartFile> otherFiles,
            String title,
            String content,
            LocalDateTime startDate,
            LocalDateTime endDate,
            LocalDateTime winningDate) throws IOException {
        try {
            // Thumbnail Image Upload
            String thumbImageUrl = s3Service.uploadFile(thumbFile);


            // Determine EventType
            EventType determinedType = EventType.determineEventType(startDate, endDate, winningDate);

            // Create and Save Event
            Event event = Event.builder()
                    .title(title)
                    .content(content)
                    .eventType(determinedType)
                    .thumbnailUrl(thumbImageUrl)
                    .startDate(startDate)
                    .endDate(endDate)
                    .winningDate(winningDate)
                    .build();

            Event savedEvent = eventRepository.save(event); // Save the event and get the saved instance

            // Save Event Images and EventImageList
            for (MultipartFile one : otherFiles) {
                String imageUrl = s3Service.uploadFile(one);
                EventImage eventImage = EventImage.builder()
                        .event(savedEvent)
                        .imageUrl(imageUrl)
                        .build();
                EventImage savedEventImage = eventImageRepository.save(eventImage);

                // Create and Save EventImageList
                EventImageList eventImageList = new EventImageList();
                eventImageList.setEventImage(savedEventImage);
                eventImageList.setEvent(savedEvent);
                eventImageListRepository.save(eventImageList);
            }
            return true;
        } catch (DateTimeParseException e) {
            throw new EventException("날짜 형식이 잘못되었습니다.");
        } catch (IOException e) {
            throw new EventException("이미지 업로드 중 오류가 발생했습니다.");
        } catch (Exception e) {
            throw new EventException("이벤트 추가 중 오류가 발생했습니다: " + e.getMessage());
        }
    }


    @Override
    public List<Event> getAllEvents() {

        return eventRepository.findAll();
    }

    @Override
    public List<EventEntries> getEventsParticipatedByUuid(String uuid) {

        return userEventRepository.findByUuid(uuid);
    }

    @Override
    public List<EventEntries> getWinningEventsByUuid(String uuid) {
        return userEventRepository.findByUuidAndIsWinning(uuid, true);
    }

}