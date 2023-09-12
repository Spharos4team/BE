package com.spharos.ssgpoint.event.vo;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventAdd {

    private String title;
    private String content;
    private String eventType; // Assuming EventType is a String. Replace with the correct type if necessary
    private MultipartFile thumbnailFile;
    private MultipartFile bannerFile;
    private List<MultipartFile> otherFiles;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime winningDate;



}