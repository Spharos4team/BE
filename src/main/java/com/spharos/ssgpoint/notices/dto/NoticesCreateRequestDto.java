package com.spharos.ssgpoint.notices.dto;

import com.spharos.ssgpoint.notices.domain.Notices;
import com.spharos.ssgpoint.notices.domain.Notices.NoticeType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticesCreateRequestDto {

    private NoticeType type;
    private String title;
    private String content;

    public Notices toEntity() {
        return new Notices(null, type, title, content);
    }
}
