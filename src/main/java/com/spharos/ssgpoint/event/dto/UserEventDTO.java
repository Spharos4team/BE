package com.spharos.ssgpoint.event.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEventDTO {
    @Getter
    private Long id;
    @Getter
    private String uuid;
    private String eventName; // 이벤트 이름을 추가했습니다.
    private boolean isWinning; // 당첨 여부를 추가했습니다.

    // getters and setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


    public void setEventName(String eventName) {
        this.eventName = eventName;
    }


    public void setWinning(boolean isWinning) {
        this.isWinning = isWinning;
    }

    public void setEventId(Long id) {
    }
}
