package com.spharos.ssgpoint.event.infrastructure;

import com.spharos.ssgpoint.event.domain.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findByType(int i); // 이벤트 타입에 따른 이벤트 목록 조회
}