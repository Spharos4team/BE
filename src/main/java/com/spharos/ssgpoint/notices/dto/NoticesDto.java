package com.spharos.ssgpoint.notices.dto;

import com.spharos.ssgpoint.notices.domain.Notices.NoticeType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoticesDto {

    private Long id;
    private NoticeType type;
    private String title;
    private String content;
    private LocalDateTime regDate;

}
