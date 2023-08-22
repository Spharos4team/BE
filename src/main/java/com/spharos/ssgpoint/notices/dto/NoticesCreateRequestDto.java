package com.spharos.ssgpoint.notices.dto;

import com.spharos.ssgpoint.notices.domain.Notices;
import com.spharos.ssgpoint.notices.domain.Notices.NoticeType;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NoticesCreateRequestDto {

    private NoticeType type;
    private String title;
    private String content;
    private LocalDateTime regDate;

    public Notices toEntity() {
        return new Notices(null, type, title, content, regDate);
    }
}
