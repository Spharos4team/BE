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
    private Long id;
    private String uuid;
    private String eventName;
    private boolean isWinning;

    public UserEventDTO(Long id, String uuid, String eventName) {
        this.id = id;
        this.uuid = uuid;
        this.eventName = eventName;
    }
}
