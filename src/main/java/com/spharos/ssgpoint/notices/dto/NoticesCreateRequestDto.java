package com.spharos.ssgpoint.notices.dto;

import com.spharos.ssgpoint.notices.domain.Notices;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticesCreateRequestDto {

    private String title;
    private String content;

    public Notices toEntity() {
        return new Notices(null, title, content, null);
    }

}