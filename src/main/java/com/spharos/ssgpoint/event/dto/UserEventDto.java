package com.spharos.ssgpoint.event.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEventDto {

    private Long id;

    private Long eventId;

    private String UUID;

    private boolean isWinner;
}