package com.spharos.ssgpoint.notices.dto;

import com.spharos.ssgpoint.notices.domain.Notices;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NoticesDto {

    private Long id;
    private String title;
    private String content;

    public static NoticesDto fromEntity(Notices notices) {
        return new NoticesDto(notices.getId(), notices.getTitle(), notices.getContent());
    }
}
