package com.spharos.ssgpoint.notices.presentation;

import com.spharos.ssgpoint.notices.application.NoticesService;
import com.spharos.ssgpoint.notices.domain.Notices;
import com.spharos.ssgpoint.notices.dto.NoticesCreateRequestDto;
import com.spharos.ssgpoint.notices.dto.NoticesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class NoticesController {

    private final NoticesService noticesService;

    @GetMapping("/notices")
    public List<NoticesDto> getAllNotices() {
        return noticesService.findAllNotices().stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @GetMapping("/notices/{id}")
    public NoticesDto getNoticeById(@PathVariable Long id) {
        Notices notices = noticesService.findNoticeById(id);
        return convertToDto(notices);
    }

    @PostMapping("/notices")
    public NoticesDto createNotice(@RequestBody NoticesCreateRequestDto requestDto) {
        Notices notices = noticesService.createNotice(requestDto.toEntity());
        return convertToDto(notices);
    }

    @DeleteMapping("/notiecs/{id}")
    public void deleteNotice(@PathVariable Long id) {
        noticesService.deleteNotice(id);
    }

    private NoticesDto convertToDto(Notices notices) {
        return new NoticesDto(notices.getId(), notices.getTitle(), notices.getContent());
    }
}