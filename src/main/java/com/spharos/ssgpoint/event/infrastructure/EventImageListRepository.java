package com.spharos.ssgpoint.event.infrastructure;

import com.spharos.ssgpoint.event.domain.EventImageList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventImageListRepository extends JpaRepository<EventImageList, Long> {
}
