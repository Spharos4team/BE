package com.spharos.ssgpoint.event.infrastructure;

import com.spharos.ssgpoint.event.domain.EventEntries;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserEventRepository extends JpaRepository<EventEntries, Long> {

    List<EventEntries> findByUuid(String uuid);

    List<EventEntries> findByUuidAndIsWinning(String uuid, boolean isWinning);
}