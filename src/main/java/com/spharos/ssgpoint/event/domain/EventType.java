package com.spharos.ssgpoint.event.domain;

import java.time.LocalDateTime;

public enum EventType {
    ONGOING, CLOSED, WINNER;  // 진행중, 종료, 당첨자 발표

    public static EventType determineEventType(LocalDateTime startDate, LocalDateTime endDate, LocalDateTime winningDate) {
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(startDate) && now.isBefore(endDate)) {
            return ONGOING;
        } else if (now.isAfter(endDate) && now.isBefore(winningDate)) {
            return CLOSED;
        } else if (now.isAfter(winningDate)) {
            return WINNER;
        }
        throw new IllegalArgumentException("Invalid date parameters for determining event type.");
    }

}
