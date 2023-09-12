package com.spharos.ssgpoint.event.infrastructure;

import com.spharos.ssgpoint.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    @Query("SELECT e FROM Event e WHERE e.startDate <= CURRENT_DATE AND e.endDate >= CURRENT_DATE")
    List<Event> findOngoingEvents();

    @Query("SELECT e FROM Event e WHERE e.endDate < CURRENT_DATE AND (e.winningDate IS NULL OR e.winningDate > CURRENT_DATE)")
    List<Event> findClosedEvents();

    @Query("SELECT e FROM Event e WHERE e.winningDate IS NOT NULL AND e.winningDate <= CURRENT_DATE")
    List<Event> findWinnerEvents();
}