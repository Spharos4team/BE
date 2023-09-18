package com.spharos.ssgpoint.event.domain;

import io.micrometer.common.lang.Nullable;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.Set;

public enum EventType {
    ONGOING, CLOSED, WINNER;

    public static Set<EventType> determineEventTypes(LocalDateTime startDate, LocalDateTime endDate, @Nullable LocalDateTime winningDate) {
        LocalDateTime now = LocalDateTime.now();
        Set<EventType> eventTypes = EnumSet.noneOf(EventType.class);

        if (startDate != null && endDate != null) {
            if (now.isAfter(startDate) && now.isBefore(endDate)) {
                eventTypes.add(ONGOING);
            }
            if (now.isAfter(endDate)) {
                eventTypes.add(CLOSED);
            }
        }

        if (winningDate != null && now.isAfter(winningDate)) {
            eventTypes.add(WINNER);
        }

        if (eventTypes.isEmpty()) {
            throw new IllegalArgumentException("Invalid Event Date: " + startDate + " " + endDate + " " + winningDate);
        }

        return eventTypes;
    }


}