package com.spharos.ssgpoint.event.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventImageListDto {

    private Long id;
    private Long eventId;
    private String eventTitle; // 예시로 추가한 필드입니다. 필요에 따라 수정하실 수 있습니다.
    private String eventImageUrl; // EventImage의 URL을 나타냅니다.
}
