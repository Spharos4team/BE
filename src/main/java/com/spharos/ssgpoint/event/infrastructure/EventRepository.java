package com.spharos.ssgpoint.event.infrastructure;

import com.spharos.ssgpoint.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByType(Integer type);  // 'findBy' 메서드만 필요합니다.
}
