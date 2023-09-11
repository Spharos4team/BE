package com.spharos.ssgpoint.event.domain;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.Set;

public enum EventType {
    ONGOING, CLOSED, WINNER;  // 진행중, 종료, 당첨자 발표

    public static Set<EventType> determineEventTypes(LocalDateTime startDate, LocalDateTime endDate, LocalDateTime winningDate) {
        LocalDateTime now = LocalDateTime.now();
        Set<EventType> eventTypes = EnumSet.noneOf(EventType.class);

        if (now.isAfter(startDate) && now.isBefore(endDate)) {
            eventTypes.add(ONGOING);
        }
        if (now.isAfter(endDate)) {
            eventTypes.add(CLOSED);
        }
        if (now.isAfter(winningDate)) {
            eventTypes.add(WINNER);
        }

        if (eventTypes.isEmpty()) {
            throw new IllegalArgumentException("Invalid Event Date: " + startDate + " " + endDate + " " + winningDate);
        }

        return eventTypes;
    }
}
