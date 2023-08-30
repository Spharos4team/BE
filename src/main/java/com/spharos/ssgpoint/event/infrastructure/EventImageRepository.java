package com.spharos.ssgpoint.event.infrastructure;

import com.spharos.ssgpoint.event.domain.EventImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventImageRepository extends JpaRepository<EventImage, Long> {
}
