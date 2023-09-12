package com.spharos.ssgpoint.event.application;

import com.spharos.ssgpoint.event.domain.*;
import com.spharos.ssgpoint.event.dto.UserEventDTO;
import com.spharos.ssgpoint.event.exception.EventException;
import com.spharos.ssgpoint.event.infrastructure.EventImageRepository;
import com.spharos.ssgpoint.event.infrastructure.EventRepository;
import com.spharos.ssgpoint.event.infrastructure.UserEventRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Transactional
@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventImageRepository eventImageRepository;
    private final S3Service s3Service;
    private final UserEventRepository userEventRepository;

    @Override
    public List<Event> getEventsByTypes(Set<EventType> types) {
        List<Event> result = new ArrayList<>();

        if (types.contains(EventType.ONGOING)) {
            result.addAll(eventRepository.findOngoingEvents());
        }
        if (types.contains(EventType.CLOSED)) {
            result.addAll(eventRepository.findClosedEvents());
        }
        if (types.contains(EventType.WINNER)) {
            result.addAll(eventRepository.findWinnerEvents());
        }

        if (result.isEmpty()) {
            throw new IllegalArgumentException("No events found for types: " + types);
        }

        return result;
    }


    @Override
    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("다음 이벤트를 찾을수 없습니다: " + id));
    }

    @Override
    public boolean addEvent(
            MultipartFile bannerFile,
            MultipartFile thumbFile,
            List<MultipartFile> otherFiles,
            String title,
            String content,
            LocalDateTime startDate,
            LocalDateTime endDate,
            LocalDateTime winningDate) throws IOException {
        try {
            String bannerUrl = s3Service.uploadFile(bannerFile);
            String thumbImageUrl = s3Service.uploadFile(thumbFile);

            Set<EventType> determinedTypes = EventType.determineEventTypes(startDate, endDate, winningDate);

            Event event = Event.builder()
                    .title(title)
                    .content(content)
                    .eventTypes(determinedTypes)
                    .bannerUrl(bannerUrl)
                    .thumbnailUrl(thumbImageUrl)
                    .startDate(startDate)
                    .endDate(endDate)
                    .winningDate(winningDate)
                    .build();

            Event savedEvent = eventRepository.save(event);

            for (MultipartFile file : otherFiles) {
                String imageUrl = s3Service.uploadFile(file);
                EventImage eventImage = EventImage.builder()
                        .event(savedEvent)
                        .imageUrl(imageUrl)
                        .build();
                eventImageRepository.save(eventImage);
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
    public List<UserEvent> getEventsParticipatedByUuid(String uuid) {
        return userEventRepository.findByUuid(uuid);
    }


    @Override
    public List<UserEventDTO> getWinningEventsByUuid(String uuid) {
        List<UserEvent> winningEvents = userEventRepository.findByUuidAndWinning(uuid, true);

        List<UserEventDTO> winningEventDTOs = winningEvents.stream()
                .map(event -> {
                    UserEventDTO dto = new UserEventDTO();
                    dto.setId(event.getId());
                    dto.setUuid(event.getUuid());
                    dto.setEventName(event.getEvent().getTitle()); // 이벤트 이름을 설정합니다.
                    dto.setWinning(event.isWinning()); // 당첨 여부를 설정합니다.
                    return dto;
                })
                .collect(Collectors.toList());

        return winningEventDTOs;
    }




    @Override
    public void assignEventToUuid(String uuid, Long eventId) {
        try {
            Event event = eventRepository.findById(eventId)
                    .orElseThrow(() -> new EntityNotFoundException("해당 ID의 이벤트가 존재하지 않습니다: " + eventId));

            UserEvent existingUserEvent = userEventRepository.findByUuidAndEventId(uuid, eventId);
            if (existingUserEvent != null) {
                throw new EventException("이 사용자는 이미 이 이벤트에 참여했습니다.");
            }

            UserEvent userEvent = new UserEvent();
            userEvent.setUuid(uuid);
            userEvent.setEvent(event);
            userEvent.setWinning(false); // Initially, the user is not a winner

            userEventRepository.save(userEvent);
        } catch (EntityNotFoundException e) {
            throw new EventException(e.getMessage());
        } catch (Exception e) {
            throw new EventException("이벤트 할당 중 오류가 발생했습니다: " + e.getMessage());
        }
    }

    @Override
    public void assignWinnerToUuid(String uuid, Long eventId) {
try {
            UserEvent userEvent = userEventRepository.findByUuidAndEventId(uuid, eventId);
            if (userEvent == null) {
                throw new EventException("이벤트에 참여하지 않은 사용자입니다.");
            }
            userEvent.setWinning(true);
            userEventRepository.save(userEvent);
        } catch (Exception e) {
            throw new EventException("이벤트 당첨자 지정 중 오류가 발생했습니다: " + e.getMessage());
        }

    }
}






