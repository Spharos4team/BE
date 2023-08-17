package com.spharos.ssgpoint.notice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NoticeDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime postedDate;
}
