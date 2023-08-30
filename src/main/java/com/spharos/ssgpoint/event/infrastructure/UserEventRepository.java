package com.spharos.ssgpoint.event.infrastructure;

import com.spharos.ssgpoint.event.domain.EventEntries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEventRepository extends JpaRepository<EventEntries, Long> {
}